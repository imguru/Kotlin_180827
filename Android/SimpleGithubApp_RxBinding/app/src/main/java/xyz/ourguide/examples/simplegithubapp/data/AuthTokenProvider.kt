package xyz.ourguide.examples.simplegithubapp.data

import android.content.Context
import android.preference.PreferenceManager

object AuthTokenProvider {
    private const val KEY_AUTH_TOKEN = "auth_token"

    fun updateToken(context: Context, token: String) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(KEY_AUTH_TOKEN, token)
                .apply()
    }

    fun getToken(context: Context): String? {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(KEY_AUTH_TOKEN, null)
    }
}