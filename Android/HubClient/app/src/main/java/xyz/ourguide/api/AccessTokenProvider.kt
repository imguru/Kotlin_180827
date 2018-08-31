package xyz.ourguide.api

import android.content.Context
import android.preference.PreferenceManager

private const val KEY_AUTH_ACCESS_TOKEN = "kr.co.imguru.hubclient.auth_token"

// var context: Context? = null
// final class AccessTokenProviderKt {
//      static Context context = null
// }

fun updateToken(context: Context, token: String) {
    PreferenceManager.getDefaultSharedPreferences(context).edit().apply {
        putString(KEY_AUTH_ACCESS_TOKEN, token)
    }.apply()
}

fun getToken(context: Context) : String? {
    return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_AUTH_ACCESS_TOKEN, null)
}