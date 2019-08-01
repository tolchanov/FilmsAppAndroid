package com.movie.films.mvp.view

import com.arellomobile.mvp.MvpView
import com.movie.films.mvp.model.entity.Movie

interface FavoriteMoviesFragmentView : MvpView {
    fun moviesLoaded(movies: List<Movie>)
    fun showProgress()
    fun hideProgress()
    fun showErrorMessage(errorMessage: Int)
    fun onMovieRemovedFromFavorites(position: Int)
    fun showMovieDetailsScreen(movie: Movie)
    fun doLogout()
}