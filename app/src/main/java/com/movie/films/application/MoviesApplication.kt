package com.movie.films.application

import android.app.Application
import com.movie.films.di.appComponent
import org.koin.android.ext.android.startKoin

class MoviesApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appComponent)
    }
}