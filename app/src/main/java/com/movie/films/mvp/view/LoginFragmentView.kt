package com.movie.films.mvp.view

import com.arellomobile.mvp.MvpView

interface LoginFragmentView : MvpView{

    fun showUsernameIsEmptyMessage(message: Int)
    fun showErrorMessage(message: Int)
    fun goToMoviesScreen()
    fun showCreateNewUserDialog(username: String)
}