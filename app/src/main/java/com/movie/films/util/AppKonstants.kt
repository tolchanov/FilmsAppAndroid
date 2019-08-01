package com.movie.films.util

const val URL_BASE = "http://46.101.218.241/api/"
const val URL_GET_USER = "users"
const val URL_CREATE_USER = "users.json"
const val URL_ALL_MOVIES = "movies"
const val URL_FAVORITE_MOVIES = "users/{user_id}/movies"
const val URL_MAKE_MOVIE_FAVORITE = "/api/users/{user_id}/movies/{movie_id}/favorite"
const val URL_MAKE_MOVIE_UNFAVORITE = "users/{user_id}/movies/{movie_id}/unfavorite"

const val DEFAULT_TIMEOUT: Long  = 60

const val MOVIE_APP_PREFERENCES = "movie_app_prefrences"
const val PREFS_USER = "movie_app_user"

const val MOVIE_EXTRA = "movie_extra"