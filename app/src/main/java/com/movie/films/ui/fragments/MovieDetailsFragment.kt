package com.movie.films.ui.fragments

import android.app.AppComponentFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.movie.films.R
import com.movie.films.mvp.model.entity.Movie
import com.movie.films.mvp.presenter.MovieDetailsFragmentPresenter
import com.movie.films.mvp.view.MovieDetailsFragmentView
import com.movie.films.util.MOVIE_EXTRA
import kotlinx.android.synthetic.main.fragment_movie_details.*


class MovieDetailsFragment : BaseFragment(), MovieDetailsFragmentView {

    @InjectPresenter
    lateinit var movieDetailsFragmentPresenter: MovieDetailsFragmentPresenter

    lateinit var movie: Movie

    companion object {
        fun newInstance(movie: Movie): MovieDetailsFragment {
            val args = Bundle()
            args.putSerializable(MOVIE_EXTRA, movie)
            val fragment = MovieDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        movie = arguments?.getSerializable(MOVIE_EXTRA) as Movie
        return inflater.inflate(R.layout.fragment_movie_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        movieDetailsFragmentPresenter.onFragmentCreated(movie)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(toolbar as Toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        super.onViewCreated(view, savedInstanceState)

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        movieDetailsFragmentPresenter.onMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    override fun showMovieData(movie: Movie) {
        Glide.with(activity!!).load(movie.thumbnail).apply(RequestOptions().placeholder(R.drawable.thumbnail_stub)).into(ivMovieThumbnail)

        val sb = StringBuilder()
        for (element in movie.getGentresArr()) {
            sb.append(element.name).append(", ")
        }
        val genres = sb.toString().substring(0, sb.length-2)

        tvTitle.text = movie?.name
        tvYear.text = getString(R.string.year, movie?.year)
        tvDirector.text = getString(R.string.director, movie?.director)
        tvMainStar.text = getString(R.string.main_star, movie?.mainStar)
        tvGenres.text = getString(R.string.genres, genres)
        tvTimesLiked.text= getString(R.string.times_liked, 5)
        tvDescription.text = getString(R.string.description, movie?.description)

        (activity as AppCompatActivity).supportActionBar?.title = movie.name
    }

    override fun closeScreen() {
        activity?.finish()
    }

}