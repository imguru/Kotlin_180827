package xyz.ourguide.examples.simplegithubapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_repository.*
import xyz.ourguide.examples.simplegithubapp.R
import xyz.ourguide.examples.simplegithubapp.api.GithubApi
import xyz.ourguide.examples.simplegithubapp.api.GithubApiProvider
import java.text.SimpleDateFormat
import java.util.*

class RepositoryActivity : AppCompatActivity() {

    private lateinit var api: GithubApi

    private val dateFormatInResponse = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault())

    private val dateFormatToShow = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)


        api = GithubApiProvider.provideGithubApi(this)
        val login = intent.getStringExtra("user_login")
        val repo = intent.getStringExtra("repo_name")

        check(login != null)
        check(repo != null)

        showRepositaryInfo(login, repo)
    }

    private fun showRepositaryInfo(login: String, repoName: String) {
        showProgress()

        val call = api.getRepository(login, repoName)
        call.enqueue({ response ->
            hideProgress(true)

            val repo = response.body()
            if (response.isSuccessful && repo != null) {
                GlideApp.with(this)
                        .load(repo.owner.avatarUrl)
                        .into(ivActivityRepositoryProfile)

                tvActivityRepositoryName.text = repo.fullName
                tvActivityRepositoryStars.text = resources
                        .getQuantityString(R.plurals.star, repo.stars, repo.stars)

                tvActivityRepositoryDescription.text =
                        repo.description ?: getString(R.string.no_description_provided)
                tvActivityRepositoryLanguage.text =
                        repo.language ?: getString(R.string.no_language_specified)

                val lastUpdate = dateFormatInResponse.parse(repo.updatedAt)
                tvActivityRepositoryLastUpdate.text = dateFormatToShow.format(lastUpdate)
            } else {
                showError("Not successful: ${response.message()}")
            }

        }, {
            hideProgress(false)
            showError(it.message!!)
        })
    }

    private fun showProgress() {
        llActivityRepositoryContent.visibility = View.GONE
        pbActivityRepository.visibility = View.VISIBLE
    }

    private fun hideProgress(isSucceed: Boolean) {
        llActivityRepositoryContent.visibility = when {
            isSucceed -> View.VISIBLE
            else -> View.GONE
        }

        pbActivityRepository.visibility = View.GONE
    }

    private fun showError(message: String) {
        tvActivityRepositoryMessage.text = message
        tvActivityRepositoryMessage.visibility = View.VISIBLE
    }
}