package com.movie.films.mvp.view

import com.arellomobile.mvp.MvpView

interface MoviesActivityView : MvpView {
    fun setToolbarTitle(username: String?)
    fun doLogout()
}