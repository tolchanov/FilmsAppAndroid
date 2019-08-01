package com.movie.films.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.movie.films.R
import com.movie.films.mvp.model.Model
import com.movie.films.mvp.view.LoginFragmentView
import com.movie.films.util.ConnectionChecker
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

@InjectViewState
class LoginFragmentPresenter : MvpPresenter<LoginFragmentView>(), KoinComponent{

    private var username: String? = null
    val model: Model by inject()
    val connectionChecker: ConnectionChecker by inject()

    override fun onFirstViewAttach(){
        super.onFirstViewAttach()
        val user = model.getUserFromPrefs()
        if (!user.username.isNullOrEmpty()){
            viewState.goToMoviesScreen()
        }
    }

    fun usernameChanged(username: String){
        this.username = username
    }

    fun goButtonClicked(){
        if (username.isNullOrEmpty()){
            viewState.showUsernameIsEmptyMessage(R.string.username_is_empty)
        }
        else if (username!!.contains(("\\s+").toRegex())){
            viewState.showUsernameIsEmptyMessage(R.string.enter_valid_name)
        }
        else{
            if (!connectionChecker.isNetworkAvailable()){
                viewState.showErrorMessage(R.string.no_conntextion_message)
                return
            }
            username?.also {
                model.getUser(it)
                    .subscribe(
                        {users->
                            model.saveUserToPrefs(users[0])
                            viewState.goToMoviesScreen()
                        },
                        {throwable->
                            viewState.showCreateNewUserDialog(it)
                        }
                    )
            }

        }
    }

    fun onPositiveButtonClick(username: String){
        createNewUser(username)
    }


    fun createNewUser(username: String){
        model.setUser(username)
                .subscribe(
                        {user->
                            model.saveUserToPrefs(user)
                            viewState.goToMoviesScreen()
                        },
                        {error->
                            viewState.showErrorMessage(R.string.failed_to_create_user)
                        }
                )
    }

}