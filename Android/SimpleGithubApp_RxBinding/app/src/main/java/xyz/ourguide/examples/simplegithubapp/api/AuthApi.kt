package xyz.ourguide.examples.simplegithubapp.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import xyz.ourguide.examples.simplegithubapp.api.model.GithubAccessToken


interface AuthApi {
    @FormUrlEncoded
    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    fun getAccessToken(
            @Field("client_id") clientId: String,
            @Field("client_secret") clientSecret: String,
            @Field("code") code: String): Call<GithubAccessToken>
}