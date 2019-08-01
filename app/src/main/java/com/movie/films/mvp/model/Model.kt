package com.movie.films.mvp.model

import com.movie.films.mvp.model.entity.Movie
import com.movie.films.mvp.model.entity.User
import io.reactivex.Observable

interface Model {
    fun getUser(username: String): Observable<List<User>>
    fun setUser(username: String): Observable<User>
    fun saveUserToPrefs(user: User)
    fun getUserFromPrefs(): User
    fun getAllMovies(): Observable<List<Movie>>
    fun getFavoriteMovies(userId: Int): Observable<List<Movie>>
    fun makeMovieFavorite(userId: Int, movieId: Int): Observable<List<Movie>>
    fun makeMovieUnvavorite(userId: Int, movieId: Int): Observable<List<Movie>>
    fun logout()
}