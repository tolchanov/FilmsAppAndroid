package com.movie.films.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.arellomobile.mvp.presenter.InjectPresenter
import com.movie.films.R
import com.movie.films.mvp.presenter.MoviesActivityPresenter
import com.movie.films.mvp.view.MoviesActivityView

import com.movie.films.ui.adapters.MoviesFragmentViewPagerAdapter
import kotlinx.android.synthetic.main.activity_movies.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*


class MoviesActivity : BaseActivity(), MoviesActivityView {

    @InjectPresenter
    lateinit var moviesActivityPresenter: MoviesActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        setSupportActionBar(toolbar as Toolbar)

        val moviesFragmentViewPagerAdapter = MoviesFragmentViewPagerAdapter(supportFragmentManager, resources.getStringArray(R.array.movies_tabs))
        vpMoviesFragments.adapter = moviesFragmentViewPagerAdapter

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.movies_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        moviesActivityPresenter.onMenuItemClicked(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    override fun doLogout() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()

    }

    override fun setToolbarTitle(username: String?) {
        supportActionBar?.title = getString(R.string.welcome, username)
    }

    fun getToolbar(): Toolbar{
        return toolbar as Toolbar
    }
}