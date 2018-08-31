package xyz.ourguide.examples.simplegithubapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ourguide.examples.simplegithubapp.BuildConfig
import xyz.ourguide.examples.simplegithubapp.R
import xyz.ourguide.examples.simplegithubapp.api.GithubApiProvider
import xyz.ourguide.examples.simplegithubapp.data.AuthTokenProvider


// 1. Anko Setting 하기
//  app - build.gradle
//    implementation "org.jetbrains.anko:anko-commons:0.10.5"

class SignInActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // 2. Github Login
        // https://github.com/login/oauth/authorize?client_id=f39af2137de7fafdd296
        btnActivitySignInStart.setOnClickListener {
            val authUri = Uri.Builder().scheme("https").authority("github.com")
                    .appendPath("login")
                    .appendPath("oauth")
                    .appendPath("authorize")
                    .appendQueryParameter("client_id", BuildConfig.GITHUB_CLIENT_ID)
                    .build()

            toast(authUri.toString())
            val intent = CustomTabsIntent.Builder().build()
            intent.launchUrl(this, authUri)
        }
    }

    // Redirect Intent 처리
    /*
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />

        <data
            android:host="authorize"
            android:scheme="simplegithub" />
    </intent-filter>
    */
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        showProgress()
        toast("OnNewIntent")

        val uri = intent?.data
                ?: throw IllegalStateException("No data exist")

        val code = uri.getQueryParameter("code")
                ?: throw IllegalStateException("No code exist")

        getAccessToken(code)
    }

    // Github API를 통해서 AccessToken을 얻어와야 합니다.
    private fun getAccessToken(code: String) {
        val api = GithubApiProvider.authApi

        val accessTokenCall = api.getAccessToken(
                clientId = BuildConfig.GITHUB_CLIENT_ID,
                clientSecret = BuildConfig.GITHUB_CLIENT_SECRET,
                code = code
        )

        accessTokenCall.enqueue({ response ->
            hideProgress()

            val token = response.body()
            if (response.isSuccessful && token != null) {

                AuthTokenProvider.updateToken(this@SignInActivity,
                        token.accessToken)

                launchMainActivity()

            } else {
                showError(IllegalStateException("Failed: ${response.message()}"))
            }
        }, {
            hideProgress()
            showError(it)
        })

        /*
        accessTokenCall.enqueue(object : Callback<GithubAccessToken> {
            override fun onFailure(call: Call<GithubAccessToken>, t: Throwable) {
                hideProgress()
                showError(t)
            }

            override fun onResponse(call: Call<GithubAccessToken>,
                                    response: Response<GithubAccessToken>) {
                hideProgress()

                val token = response.body()
                if (response.isSuccessful && token != null) {

                    AuthTokenProvider.updateToken(this@SignInActivity,
                            token.accessToken)

                    launchMainActivity()

                } else {
                    showError(IllegalStateException("Failed: ${response.message()}"))
                }
            }

        })
        */

    }

    private fun launchMainActivity() {
        val intent = intentFor<MainActivity>()
                .clearTask()
                .newTask()
        startActivity(intent)
    }

    private fun showError(throwable: Throwable) {
        longToast(throwable.message!!)
    }

    private fun showProgress() {
        btnActivitySignInStart.visibility = View.GONE
        pbActivitySignIn.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        btnActivitySignInStart.visibility = View.VISIBLE
        pbActivitySignIn.visibility = View.GONE
    }
}


fun <T> Call<T>.enqueue(success: (response: Response<T>) -> Unit,
                        failure: (t: Throwable) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>) = success(response)

        override fun onFailure(call: Call<T>?, t: Throwable) = failure(t)
    })
}
