package com.movie.films.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.movie.films.R
import com.movie.films.mvp.model.Model
import com.movie.films.mvp.model.entity.FavoriteMoviesContainer
import com.movie.films.mvp.model.entity.Movie
import com.movie.films.mvp.model.entity.User
import com.movie.films.mvp.view.FavoriteMoviesFragmentView
import com.movie.films.util.ConnectionChecker
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

@InjectViewState
class FavoriteMoviesFragmentPresenter : MvpPresenter<FavoriteMoviesFragmentView>(), KoinComponent{
    private val model: Model by inject()
    private val favoriteMoviesContainer: FavoriteMoviesContainer by inject()
    private val connectionChecker: ConnectionChecker by inject()

    private lateinit var user: User

    override fun onFirstViewAttach(){
        super.onFirstViewAttach()
        user = model.getUserFromPrefs()

    }

    fun onResumeAction(){
        loadMovies()
    }


    fun loadMovies(){
        if (!connectionChecker.isNetworkAvailable()){
            viewState.showErrorMessage(R.string.no_conntextion_message)
            return
        }

        val disposable = model.getUser(user.username!!)
                .subscribe(
                        {users->
                            viewState.showProgress()
                            model.getFavoriteMovies(user.id!!)
                                    .subscribe (
                                            {movies->
                                                viewState.hideProgress()
                                                viewState.moviesLoaded(movies)
                                                favoriteMoviesContainer.setNewMovies(ArrayList(movies))
                                            },
                                            {throwable->
                                                viewState.hideProgress()
                                                viewState.showErrorMessage(R.string.failed_to_get_movies)
                                            }
                                    )
                        },
                        {throwable->
                            model.logout()
                            viewState.doLogout()
                        }
                )

    }


    fun unvaforiteMovie(movieId: Int, position: Int){
        if (!connectionChecker.isNetworkAvailable()){
            viewState.showErrorMessage(R.string.no_conntextion_message)
            return
        }
        model.getUser(user.username!!)
                .subscribe(
                        {users->
                            viewState.showProgress()
                            model.makeMovieUnvavorite(user.id!!, movieId)
                                    .subscribe(
                                            {movies->
                                                viewState.hideProgress()
                                                viewState.onMovieRemovedFromFavorites(position)
                                                favoriteMoviesContainer.setNewMovies(ArrayList(movies))
                                            },
                                            {throwable->
                                                viewState.hideProgress()
                                                viewState.showErrorMessage(R.string.failed_to_remove_from_favorites)
                                            }
                                    )
                        },
                        {throwable->
                            model.logout()
                            viewState.doLogout()
                        }
                )


    }

    fun onMovieItemClicked(movie: Movie){
        viewState.showMovieDetailsScreen(movie)
    }
}