package com.movie.films.ui.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.movie.films.R
import com.movie.films.mvp.model.entity.Movie
import kotlinx.android.synthetic.main.movie_item.view.*
import org.koin.standalone.KoinComponent

class MovieItemHolder (itemView: View, private val onMovieClickListener: MoviesRecyclerAdapter.OnMovieClickListener) : RecyclerView.ViewHolder(itemView){
    private lateinit var movie: Movie
    private var isInFavorites: Boolean? = false
    fun bind(movie: Movie, context: Context, isInFavorites: Boolean){
        this.movie = movie
        this.isInFavorites = isInFavorites
        itemView.tvMovieTitle.text = movie.name
        when (isInFavorites){
            true->{itemView.ivFavUnfav.setBackgroundResource(R.drawable.ic_star_favorite)}
            else->{itemView.ivFavUnfav.setBackgroundResource(R.drawable.ic_star_unfavorite)}
        }
        Glide.with(context).load(movie.thumbnail).apply(RequestOptions().placeholder(R.drawable.thumbnail_stub)).into(itemView.ivThumbnail)
        itemView.setOnClickListener { v-> onMovieClickListener.onItemClicked(movie, adapterPosition) }
        itemView.ivFavUnfav.setOnClickListener{v-> onMovieClickListener.onFavClicked(movie.id!!, adapterPosition, isInFavorites)}
    }
}