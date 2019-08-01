package com.movie.films.mvp.model

import com.google.gson.Gson
import com.movie.films.di.modules.PrefsModule
import com.movie.films.mvp.model.Model
import com.movie.films.mvp.model.api.ApiInterface
import com.movie.films.mvp.model.entity.Movie
import com.movie.films.mvp.model.entity.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class ModelImplementation : Model, KoinComponent {

    private val apiInterface: ApiInterface by inject()
    private val prefsModule: PrefsModule by inject()

    override fun getUser(username: String): Observable<List<User>>{
        return apiInterface.getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
    override fun setUser(username: String): Observable<User>{
        var userResquest = """{"user": {"username": "$username"}}"""
        var reqBody = RequestBody.create(MediaType.parse("text/plain"), userResquest)

        return apiInterface.setUser(reqBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun saveUserToPrefs(user: User){
        var gson = Gson()
        var userStr = gson.toJson(user)
        prefsModule.saveUserToPrefs(userStr)
    }

    override fun getUserFromPrefs(): User{
        var gson = Gson()
        return gson.fromJson(prefsModule.getUserFromPrefs(), User::class.java)
    }

    override fun getAllMovies(): Observable<List<Movie>> {
        return apiInterface.getAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFavoriteMovies(userId: Int): Observable<List<Movie>> {
        return apiInterface.getFavoriteMovies(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


    override fun makeMovieFavorite(userId: Int, movieId: Int): Observable<List<Movie>>{
        return apiInterface.makeMovieFavorite(userId, movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun makeMovieUnvavorite(userId: Int, movieId: Int): Observable<List<Movie>> {
        return apiInterface.makeMovieUnfavorite(userId, movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun logout() {
        prefsModule.logout()
    }

}