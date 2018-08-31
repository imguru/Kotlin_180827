package xyz.ourguide.hubclient

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import xyz.ourguide.hubclient.common.GITHUB_CLIENT_ID


// 1. github.com - Social(OAuth) Login
//   => Web Browser
//   => code 받습니다.

// 2. api.github.com - Login
//   => Access Token 을 받습니다.

// 3. api.github.com
//   => 요청을 할 때마다 AccessToken을 HTTP 헤더에 포함해서 요청을 해야 합니다.
class SignInActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInButton.setOnClickListener { loginWithGithub() }
    }

    // Activity가 Intent를 받았을 때 호출되는 함수
    override fun onNewIntent(intent: Intent?) {
        // github.com이 로그인에 성공하였을 때, 주는 code 값을 읽어야 합니다.
        info("onNewIntent")
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









