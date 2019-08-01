package com.movie.films.mvp.model.api

import com.movie.films.mvp.model.entity.Movie
import com.movie.films.mvp.model.entity.User
import com.movie.films.util.*
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiInterface {

    @GET(URL_GET_USER)
    @Headers("Content-Type: application/json")
    fun getUser(@Query("username") username: String): Observable<List<User>>

    @POST(URL_CREATE_USER)
    @Headers("Content-Type: application/json")
    fun setUser(@Body body: RequestBody): Observable<User>

    @GET(URL_ALL_MOVIES)
    @Headers("Content-Type: application/json")
    fun getAllMovies(): Observable<List<Movie>>

    @GET(URL_FAVORITE_MOVIES)
    @Headers("Content-Type: application/json")
    fun getFavoriteMovies(@Path("user_id") userId: Int): Observable<List<Movie>>

    @GET(URL_MAKE_MOVIE_FAVORITE)
    @Headers("Content-Type: application/json")
    fun makeMovieFavorite(@Path("user_id") userId: Int, @Path("movie_id") movieId: Int): Observable<List<Movie>>

    @GET(URL_MAKE_MOVIE_UNFAVORITE)
    @Headers("Content-Type: application/json")
    fun makeMovieUnfavorite(@Path("user_id") userId: Int, @Path("movie_id") movieId: Int): Observable<List<Movie>>

}