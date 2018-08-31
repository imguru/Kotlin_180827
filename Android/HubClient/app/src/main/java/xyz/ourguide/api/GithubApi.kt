package xyz.ourguide.api

import android.content.Context
import com.google.gson.annotations.SerializedName
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// REST API / Graph QL

/*
interface GithubApi {
    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String): Call<RepoSearchResponse>
}
*/

// Retrofit의 결과를 Rx로 사용하기 위해서는 반환타입이 변경된다.
// Rx의 요소
//  동기
//   a()
//   b()
//   c()

//  비동기 - 완료시점을 명확하게 알 수 없다.
//   -> 완료 시점에 호출되는 콜백을 등록
//   a(() -> {
//      b(() -> {
//         c()
//      })
//   })
//   -> 코드의 흐름을 제어하기 어렵다.
//   -> 콜백을 중첩해서 사용하면, 콜백 헬에 빠질 수 있다.

// 비동기 흐름을 제어하는 방법
//  Javascript -> Promise Pattern(async / await)
//  C#         -> Coroutine

//  Java       -> Rx(Reactive Extension)
//    : RxJava, RxGo, RxSwift, RxKotlin

// * Rx 장점
//  Collection을 다루는 일반적인 연산의 조합처럼, 비동기 이벤트를 일반적인 연산의 조합을 통해
//  제어하는 것이 가능하다.

// cities.map{}.filter{}

// Collection: Iterable    <- Iterator
// Rx        : Observable  <- Observer
// 1. Observable
// : 이벤트를 만들어내는 주체로, 이벤트 스트림을 통해 만든 이벤트를 내보냅니다.

// 2. Observer
// : Observable에서 만든 이벤트에 반응(react)하며, 이벤트를 받았을 때 수행할 작업을 정의합니다.

// 3. Operator
// : map{}.filter{}
//   이벤트에 연관된 값을 변환하거나, 필터하거나, 하는 일반적인 작업

// 4. Scheduler
// : 작업을 수행할 스레드를 지정하는 것이 가능합니다.

// 5. Disposable
// : [Observable <- Observer] -> Disposable
//   명시적으로 정리하지 않으면, 누수가 발생합니다.


interface GithubApi {
    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String): Call<RepoSearchResponse>
}

data class GithubOwner(val login: String,
                       @field:SerializedName("avatar_url") val avatarUrl: String)

data class GithubRepo(val name: String,
                      @field:SerializedName("full_name") val fullName: String,
                      val owner: GithubOwner,
                      val description: String,
                      val language: String)

data class RepoSearchResponse(@field:SerializedName("total_count") val totalCount: Int,
                              val items: List<GithubRepo>)


private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BASIC
}

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder().apply {
            addHeader("Authorization", "bearer ${getToken(context)}")
        }.build()

        return chain.proceed(request)
    }
}

private fun getHttpClient(context: Context) = OkHttpClient.Builder().apply {
    addInterceptor(loggingInterceptor)
    addInterceptor(AuthInterceptor(context))
}.build()

// Application Context
internal fun githubApi(context: Context): GithubApi = Retrofit.Builder().apply {
    baseUrl("https://api.github.com/")
    client(getHttpClient(context))
    addConverterFactory(GsonConverterFactory.create())
}.build().create(GithubApi::class.java)







