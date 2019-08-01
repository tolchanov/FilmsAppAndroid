package com.movie.films.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.movie.films.R
import com.movie.films.mvp.model.Model
import com.movie.films.mvp.model.entity.FavoriteMoviesContainer
import com.movie.films.mvp.model.entity.Movie
import com.movie.films.mvp.model.entity.User
import com.movie.films.mvp.view.MoviesFragmentView
import com.movie.films.util.ConnectionChecker
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

@InjectViewState
class MoviesFragmentPresenter : MvpPresenter<MoviesFragmentView>(), KoinComponent {
    private val model: Model by inject()
    private lateinit var user: User
    private val favoriteMoviesContainer: FavoriteMoviesContainer by inject()
    private val connectionChecker: ConnectionChecker by inject()

    override fun onFirstViewAttach(){
        super.onFirstViewAttach()
        user = model.getUserFromPrefs()
        loadFavoriteMovies()
    }



    fun loadFavoriteMovies(){
        if (!connectionChecker.isNetworkAvailable()){
            viewState.showErrorMessage(R.string.no_conntextion_message)
            return
        }

        model.getUser(user.username!!)
                .subscribe(
                        {users->
                            model.getFavoriteMovies(user.id!!)
                                    .subscribe (
                                            {movies->
                                                favoriteMoviesContainer.setNewMovies(ArrayList(movies))
                                                loadMovies()
                                            },
                                            {throwable->
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






    fun onFavClicked(movieId: Int, position: Int, isInFavorites: Boolean){
        if (!connectionChecker.isNetworkAvailable()){
            viewState.showErrorMessage(R.string.no_conntextion_message)
            return
        }
        model.getUser(user.username!!)
                .subscribe(
                        {users->
                            when (!isInFavorites){
                                true->{
                                    model.makeMovieFavorite(user.id!!, movieId)
                                            .subscribe (
                                                    {movies->
                                                        viewState.hideProgress()
                                                        favoriteMoviesContainer.setNewMovies(ArrayList(movies))
                                                        viewState.updateItem(position)
                                                    },
                                                    {throwable->
                                                        viewState.hideProgress()
                                                        viewState.showErrorMessage(R.string.failed_to_add_to_favorites)
                                                    }
                                            )
                                }
                                else->{
                                    model.makeMovieUnvavorite(user.id!!, movieId)
                                            .subscribe (
                                                    {movies->
                                                        viewState.hideProgress()
                                                        favoriteMoviesContainer.setNewMovies(ArrayList(movies))
                                                        viewState.updateItem(position)
                                                    },
                                                    {throwable->
                                                        viewState.hideProgress()
                                                        viewState.showErrorMessage(R.string.failed_to_add_to_favorites)
                                                    }
                                            )
                                }
                            }
                        },
                        {throwable->
                            model.logout()
                            viewState.doLogout()
                        }

                )


    }

    fun loadMovies(){
        viewState.showProgress()
        model.getAllMovies()
                .subscribe (
                        {movies->
                            viewState.hideProgress()
                            viewState.moviesLoaded(movies)
                        },
                        {throwable->
                            viewState.hideProgress()
                            viewState.showErrorMessage(R.string.failed_to_get_movies)
                        }
                )
    }

    fun onResumeAction(){
        viewState.updateItems()
    }

    fun onMovieItemClicked(movie: Movie){
        viewState.showMovieDetailsScreen(movie)
    }
}