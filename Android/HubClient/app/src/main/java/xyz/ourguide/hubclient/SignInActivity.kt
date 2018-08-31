package xyz.ourguide.hubclient

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.ourguide.api.GithubAccessToken
import xyz.ourguide.api.authApi
import xyz.ourguide.api.githubApi
import xyz.ourguide.api.updateToken
import xyz.ourguide.hubclient.common.GITHUB_CLIENT_ID
import xyz.ourguide.hubclient.common.GITHUB_CLIENT_SECRET


// 1. github.com - Social(OAuth) Login
//   => Web Browser
//   => code 받습니다.

// 2. github.com - Login
//   => Access Token 을 받습니다.
//      POST - github.com/login/oauth/access_token
//             client_id / client_secret / code
//      POSTMAN

//   => Retrofit + OkHttp + Gson

// 3. api.github.com
//   => 요청을 할 때마다 AccessToken을 HTTP 헤더에 포함해서 요청을 해야 합니다.


class SignInActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInButton.setOnClickListener { loginWithGithub() }

        searchButton.setOnClickListener {
            search()
        }
    }

    // Activity가 Intent를 받았을 때 호출되는 함수
    override fun onNewIntent(intent: Intent?) {
        // github.com이 로그인에 성공하였을 때, 주는 code 값을 읽어야 합니다.
        info("onNewIntent")
        // toast("onNewIntent")

        if (intent == null)
            return

        // hubclient://authorize?code=XXXXXXX
        val uri: Uri? = intent.data
        val code: String? = uri?.getQueryParameter("code")

        if (code == null) {
            toast("code가 존재하지 않습니다.")
            return
        }

        info("code: $code")
        getAccessToken(code)
    }

    private fun search() {
        // EditText에서 데이터를 읽어와서 처리하는 로직
        val text = searchEditText.text.toString()
        if (text.isEmpty()) {
            return
        }

        // EditText의 내용이 변경될 때마다, 요청을 날리고 싶다.
        githubApi(this)
                .searchRepositories(text)
                .enqueue {
                    val response = it.body()
                    if (it.isSuccessful && response != null) {
                        val items = response.items
                        for (e in items) {
                            info(e)
                        }
                    }
                }

    }

    private fun showError(throwable: Throwable) {
        longToast(throwable.message ?: "error")
    }

    private fun getAccessToken(code: String) {
        val call = authApi.getAccessToken(GITHUB_CLIENT_ID,
                GITHUB_CLIENT_SECRET, code)

        // call.execute() // 동기 - 메인 스레드에서 사용할 경우 예외가 발생한다.
        //                         NetworkOnMainThread
        /*
        call.enqueue({ error ->
            showError(error)
        }, { response ->
            val accessToken = response.body()
            if (response.isSuccessful && accessToken != null) {
                info("${accessToken.tokenType} ${accessToken.accessToken}")
                updateToken(this, accessToken.accessToken)
            }
        })
        */


        call.enqueue { response ->
            val accessToken = response.body()
            if (response.isSuccessful && accessToken != null) {
                info("${accessToken.tokenType} ${accessToken.accessToken}")
                updateToken(this, accessToken.accessToken)
            }
        }

        /*
        call.enqueue(object : Callback<GithubAccessToken> {
            override fun onFailure(call: Call<GithubAccessToken>,
                                   t: Throwable) {
                showError(t)
            }

            override fun onResponse(call: Call<GithubAccessToken>,
                                    response: Response<GithubAccessToken>) {
                val accessToken = response.body()
                if (response.isSuccessful && accessToken != null) {
                    info("${accessToken.tokenType} ${accessToken.accessToken}")
                    updateToken(this@SignInActivity, accessToken.accessToken)
                }
            }
        })
        */
    }

    private fun loginWithGithub() {
        // Github OAuth Login URL
        // : https://github.com/login/oauth/authorize?client_id=<CLIENT_ID>

        val uri = Uri.Builder().apply {
            scheme("https")
            authority("github.com")
            appendPath("login")
            appendPath("oauth")
            appendPath("authorize")
            appendQueryParameter("client_id", GITHUB_CLIENT_ID)
        }.build()

        val intent = CustomTabsIntent.Builder().build()
        intent.launchUrl(this, uri)
    }
}

fun <T> Call<T>.enqueue(onFail: ((Throwable) -> Unit)? = null, onResponse: (Response<T>) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            onFail?.invoke(t)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            onResponse(response)
        }
    })
}

/*
fun <T> Call<T>.enqueue(onResponse: (Response<T>) -> Unit) {
    enqueue(onResponse, null)
}
*/