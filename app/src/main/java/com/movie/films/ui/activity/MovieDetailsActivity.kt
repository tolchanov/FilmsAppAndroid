package com.movie.films.ui.activity

import android.os.Bundle
import com.movie.films.R
import com.movie.films.mvp.model.entity.Movie
import com.movie.films.ui.fragments.MovieDetailsFragment
import com.movie.films.util.MOVIE_EXTRA

class MovieDetailsActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val movie= intent.getSerializableExtra(MOVIE_EXTRA) as Movie

        val movieDetailsFragment = MovieDetailsFragment.newInstance(movie)
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, movieDetailsFragment).commit()

    }
}