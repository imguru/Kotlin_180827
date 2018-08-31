package xyz.ourguide.examples.simplegithubapp.api

import io.reactivex.Observable
import retrofit2.Call
import xyz.ourguide.examples.simplegithubapp.api.model.GithubRepo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.ourguide.examples.simplegithubapp.api.model.RepoSearchResponse


interface GithubApi {
    @GET("search/repositories")
    fun searchRepository(@Query("q") query: String): Call<RepoSearchResponse>


    @GET("search/repositories")
    fun rxSearchRepository(@Query("q") query: String): Observable<RepoSearchResponse>


    @GET("repos/{owner}/{name}")
    fun getRepository(@Path("owner") ownerLogin: String,
                      @Path("name") repoName: String): Call<GithubRepo>
}