package com.movie.films.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.films.R
import com.movie.films.mvp.model.entity.FavoriteMoviesContainer
import com.movie.films.mvp.model.entity.Movie
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MoviesRecyclerAdapter(private var movies: ArrayList<Movie>, private val context: Context, private val onMovieClickListener: OnMovieClickListener) : RecyclerView.Adapter<MovieItemHolder>(), KoinComponent{
    private val favoriteMoviesContainer: FavoriteMoviesContainer by inject()

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieItemHolder, position: Int) = holder.bind(movies[position], context, favoriteMoviesContainer.movieIsInFavorites(movies[position]))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false), onMovieClickListener)

    fun replaceItems(movies: ArrayList<Movie>){
        this.movies = movies
    }

    fun removeMovie(position: Int){
        movies.removeAt(position)
        notifyItemRemoved(position)
    }

    interface OnMovieClickListener{
        fun onItemClicked(movie: Movie, position: Int)
        fun onFavClicked( movieId: Int, position: Int, isInFavorites: Boolean)
    }
}