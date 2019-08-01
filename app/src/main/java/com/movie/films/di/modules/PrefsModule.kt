package com.movie.films.di.modules

import android.content.Context
import com.movie.films.util.MOVIE_APP_PREFERENCES
import com.movie.films.util.PREFS_USER

class PrefsModule (context: Context){
    private val sharedPreferences = context.getSharedPreferences(MOVIE_APP_PREFERENCES, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()


    fun saveUserToPrefs(username: String){
        editor.putString(PREFS_USER, username)
        editor.apply()
    }

    fun getUserFromPrefs(): String{
        return sharedPreferences.getString(PREFS_USER, "{}")
    }

    fun logout(){
        editor.remove(PREFS_USER)
        editor.apply()
    }
}