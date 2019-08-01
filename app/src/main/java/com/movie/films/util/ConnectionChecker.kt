package com.movie.films.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectionChecker(val context: Context){

    fun isNetworkAvailable(): Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}


