package com.movie.films.mvp.view

import com.arellomobile.mvp.MvpView
import com.movie.films.mvp.model.entity.Movie

interface MovieDetailsFragmentView : MvpView {
    fun showMovieData(movie: Movie)
    fun closeScreen()
}