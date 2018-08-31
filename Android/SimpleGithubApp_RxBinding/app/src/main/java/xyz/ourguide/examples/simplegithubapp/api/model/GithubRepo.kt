package xyz.ourguide.examples.simplegithubapp.api.model

import com.google.gson.annotations.SerializedName


data class GithubOwner(val login: String,
                       @field:SerializedName("avatar_url") val avatarUrl: String)

data class GithubRepo(val name: String,
                      @field:SerializedName("full_name") val fullName: String,
                      val owner: GithubOwner,
                      val description: String?,
                      val language: String?,
                      @field:SerializedName("updated_at") val updatedAt: String,
                      @field:SerializedName("stargazers_count")
                      val stars: Int)

data class RepoSearchResponse(@field:SerializedName("total_count") val totalCount: Int,
                              val items: List<GithubRepo>)

