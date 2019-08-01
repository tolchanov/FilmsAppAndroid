package com.movie.films.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.movie.films.mvp.model.entity.Movie
import com.movie.films.mvp.view.MovieDetailsFragmentView

@InjectViewState
class MovieDetailsFragmentPresenter : MvpPresenter<MovieDetailsFragmentView>(){

    override fun onFirstViewAttach(){
        super.onFirstViewAttach()

    }

    fun onFragmentCreated(movie: Movie){
        viewState.showMovieData(movie)
    }

    fun onMenuItemSelected(id: Int){
        when (id){
            android.R.id.home->viewState.closeScreen()
        }
    }
}