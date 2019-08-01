package com.movie.films.mvp.model.entity

class FavoriteMoviesContainer{
    lateinit var movies: ArrayList<Movie>

    fun setNewMovies(movies: ArrayList<Movie>){
        this.movies = movies
        println("SET NEW MOVIES: ${this.movies.size}")
    }

    fun movieIsInFavorites(movie: Movie): Boolean{
        println("CHECK IF MOVIE IS IN FAVORITES: ${movie.id}   ${movies.contains(movie)} " )
        return movies.contains(movie)
    }
}