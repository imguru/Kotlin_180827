package xyz.ourguide.examples.simplegithubapp.api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xyz.ourguide.examples.simplegithubapp.data.AuthTokenProvider


class AuthIntercepter(val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder().apply {
            addHeader("Authorization", "token $token")
        }.build()

        return chain.proceed(request)
    }
}

object GithubApiProvider {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder().apply {
        addInterceptor(loggingInterceptor)
    }.build()


    val authApi: AuthApi = Retrofit.Builder()
            .baseUrl("https://github.com/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)

    private fun authHttpClient(token: String) = OkHttpClient.Builder().apply {
        addInterceptor(loggingInterceptor)
        addInterceptor(AuthIntercepter(token))
    }.build()


    fun provideGithubApi(context: Context): GithubApi =
            Retrofit.Builder().apply {
                baseUrl("https://api.github.com/")
                client(authHttpClient(AuthTokenProvider.getToken(context)!!))
                addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                addConverterFactory(GsonConverterFactory.create())
            }.build().create(GithubApi::class.java)
}