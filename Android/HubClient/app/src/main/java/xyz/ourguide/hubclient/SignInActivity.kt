package xyz.ourguide.hubclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle


// 1. github.com - Social(OAuth) Login
//   => Web Browser
//   => code 받습니다.

// 2. api.github.com - Login
//   => Access Token 을 받습니다.

// 3. api.github.com
//   => 요청을 할 때마다 AccessToken을 HTTP 헤더에 포함해서 요청을 해야 합니다.
class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Github OAuth Login URL
        // : https://github.com/login/oauth/authorize?client_id=<CLIENT_ID>



    }
}









