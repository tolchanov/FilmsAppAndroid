package com.movie.films.ui.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.movie.films.R
import com.movie.films.mvp.model.entity.Movie
import com.movie.films.mvp.presenter.MoviesFragmentPresenter
import com.movie.films.mvp.view.MoviesFragmentView
import com.movie.films.ui.activity.LoginActivity
import com.movie.films.ui.activity.MovieDetailsActivity
import com.movie.films.ui.activity.MoviesActivity
import com.movie.films.ui.adapters.MoviesRecyclerAdapter
import com.movie.films.util.MOVIE_EXTRA
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*


class MoviesFragment : BaseFragment(), MoviesFragmentView, MoviesRecyclerAdapter.OnMovieClickListener{

    @InjectPresenter
    lateinit var moviesFragmentPresenter : MoviesFragmentPresenter

    private var moviesRecyclerAdapter: MoviesRecyclerAdapter? = null

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var toolbar: Toolbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        return inflater.inflate(R.layout.fragment_movies, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvMovies.layoutManager = linearLayoutManager

        toolbar = (activity as MoviesActivity).getToolbar()


    }

    override fun moviesLoaded(movies: List<Movie>) {
        moviesRecyclerAdapter = MoviesRecyclerAdapter(ArrayList(movies), context!!, this)
        rvMovies.adapter = moviesRecyclerAdapter
    }

    override fun onResume() {
        super.onResume()
        moviesFragmentPresenter.onResumeAction()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser){
            if (::moviesFragmentPresenter.isInitialized) {
                moviesFragmentPresenter.onResumeAction()
            }
        }
    }

    override fun showProgress(){
        toolbar.pbLoader.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        toolbar.pbLoader.visibility = View.GONE
    }

    override fun showErrorMessage(errorMessage: Int) {
        Toast.makeText(context, getString(errorMessage), Toast.LENGTH_LONG).show()
    }

    override fun onFavClicked(movieId: Int, position: Int, isInFavorites: Boolean) {
        moviesFragmentPresenter.onFavClicked(movieId, position, isInFavorites)
    }

    override fun onItemClicked(movie: Movie, position: Int) {
        moviesFragmentPresenter.onMovieItemClicked(movie)
    }

    override fun updateItem(position: Int) {
        moviesRecyclerAdapter?.notifyItemChanged(position)
    }

    override fun updateItems() {
        moviesRecyclerAdapter?.notifyDataSetChanged()
    }

    override fun showMovieDetailsScreen(movie: Movie) {
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_EXTRA, movie)
        startActivity(intent)
    }

    override fun doLogout() {
        startActivity(Intent(activity, LoginActivity::class.java))
        activity!!.finish()
    }

}