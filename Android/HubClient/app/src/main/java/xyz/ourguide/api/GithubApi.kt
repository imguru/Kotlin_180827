package xyz.ourguide.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// REST API / Graph QL

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