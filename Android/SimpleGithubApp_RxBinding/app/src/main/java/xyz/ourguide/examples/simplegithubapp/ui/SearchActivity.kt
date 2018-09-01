package xyz.ourguide.examples.simplegithubapp.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.view.inputmethod.InputMethodManager
import com.jakewharton.rxbinding2.support.v7.widget.queryTextChangeEvents
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_respositary.view.*
import org.jetbrains.anko.startActivity
import xyz.ourguide.examples.simplegithubapp.R
import xyz.ourguide.examples.simplegithubapp.api.GithubApi
import xyz.ourguide.examples.simplegithubapp.api.GithubApiProvider
import xyz.ourguide.examples.simplegithubapp.api.model.GithubRepo
import java.util.concurrent.TimeUnit

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    this.add(disposable)
}


class SearchActivity : AppCompatActivity() {
    private val adapter: SearchAdapter = SearchAdapter()
    lateinit var searchView: SearchView
    lateinit var menuSearch: MenuItem

    private val disposables = CompositeDisposable()
    private val viewDisposables = CompositeDisposable()

    override fun onStop() {
        super.onStop()
        disposables.clear()
        disposables.dispose()

        if (isFinishing) {
            viewDisposables.clear()
        }
    }

    // lateinit var api: GithubApi
    private val api: GithubApi by lazy {
        GithubApiProvider.provideGithubApi(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        rvActivitySearchList.layoutManager = LinearLayoutManager(this)
        rvActivitySearchList.adapter = adapter

        adapter.onClick = this::onItemClick

        // api = GithubApiProvider.provideGithubApi(this)
    }

    private fun onItemClick(repo: GithubRepo) {
        startActivity<RepositoryActivity>(
                "user_login" to repo.owner.login,
                "repo_name" to repo.name)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_search, menu)

        menu?.let {
            menuSearch = it.findItem(R.id.menu_activity_search_query)
            searchView = menuSearch.actionView as SearchView

            viewDisposables += searchView.queryTextChangeEvents()
                    .map { it.queryText() }
                    .filter { it.isNotEmpty() }
                    .map { it.toString() }
                    .throttleLast(100,
                            TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { query ->
                        updateTitle(query)
                        // hideSoftKeyboard()
                        // collapseSearchView()
                        searchRepository(query)
                    }

            /*
            viewDisposables += searchView.queryTextChangeEvents()
                    .filter { it.isSubmitted }
                    .map { it.queryText() }
                    .filter { it.isNotEmpty() }
                    .map { it.toString() }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { query ->
                        updateTitle(query)
                        hideSoftKeyboard()
                        collapseSearchView()
                        searchRepository(query)
                    }
            */
        }

        menuSearch.expandActionView()
        return true
    }

    private fun updateTitle(query: String) {
        supportActionBar?.subtitle = query
    }

    private fun hideSoftKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(searchView.windowToken, 0)
    }

    private fun collapseSearchView() {
        menuSearch.collapseActionView()
    }

    private fun clearResults() {
        adapter.clearItems()
        adapter.notifyDataSetChanged()
    }

    private fun searchRepository(query: String) {
        disposables += api.rxSearchRepository(query)
                .flatMap {
                    when {
                        it.totalCount == 0 -> Observable.error(IllegalStateException("No search result"))
                        else -> Observable.just(it.items)
                    }
                }
                // main thread
                .observeOn(AndroidSchedulers.mainThread())
                // 구독할 때 시작하는 작업
                .doOnSubscribe {
                    clearResults()
                    hideError()
                    showProgress()
                }
                .doOnTerminate {
                    hideProgress()
                }
                .subscribe({
                    with(adapter) {
                        items = it
                        notifyDataSetChanged()
                    }

                }, {
                    showError(it.message)
                })
    }

    private fun showProgress() {
        pbActivitySearch.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        pbActivitySearch.visibility = View.GONE
    }

    private fun showError(message: String?) {
        tvActivitySearchMessage.text = message
        tvActivitySearchMessage.visibility = View.VISIBLE
    }

    private fun hideError() {
        tvActivitySearchMessage.text = ""
        tvActivitySearchMessage.visibility = View.GONE
    }
}

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.RepositoryHolder>() {
    var items: List<GithubRepo> = emptyList()
    var onClick: ((GithubRepo) -> Unit)? = null

    private var placeholder = ColorDrawable(Color.GRAY)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RepositoryHolder(parent)
    override fun getItemCount() = items.count()
    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        val repo = items[position]

        with(holder.itemView) {
            GlideApp.with(context)
                    .load(repo.owner.avatarUrl)
                    .placeholder(placeholder)
                    .into(ivItemRepositoryProfile)

            tvItemRepositoryName.text = repo.fullName
            tvItemRepositoryLanguage.text =
                    repo.language ?: context.getText(R.string.no_language_specified)

            setOnClickListener {
                onClick?.let {
                    it(repo)
                }
            }
        }
    }

    fun clearItems() {
        items = emptyList()
    }

    class RepositoryHolder(parent: ViewGroup)
        : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_respositary, parent, false))
}