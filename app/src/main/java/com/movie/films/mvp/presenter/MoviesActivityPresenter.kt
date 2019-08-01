package com.movie.films.mvp.presenter

import android.view.MenuItem
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.movie.films.R
import com.movie.films.mvp.model.Model
import com.movie.films.mvp.view.MoviesActivityView
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

@InjectViewState
class MoviesActivityPresenter : MvpPresenter<MoviesActivityView> (), KoinComponent {
    val model : Model by inject()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setToolbarTitle(model.getUserFromPrefs().username)
    }


    fun onMenuItemClicked(itemId: Int){
        when (itemId) {
             R.id.action_logout-> {
                 model.logout()
                 viewState.doLogout()
             }
        }
    }
}