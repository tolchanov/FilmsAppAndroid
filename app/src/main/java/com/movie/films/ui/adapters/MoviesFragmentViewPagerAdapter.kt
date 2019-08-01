package com.movie.films.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.movie.films.ui.fragments.FavoriteMoviesFragment
import com.movie.films.ui.fragments.MoviesFragment

class MoviesFragmentViewPagerAdapter (fm: FragmentManager, private val tabNames: Array<String>) : FragmentPagerAdapter(fm) {

    private val count = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MoviesFragment()
            1 -> FavoriteMoviesFragment()
            else -> MoviesFragment()
        }
    }

    override fun getCount(): Int {
        return count
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNames.get(position)
    }
}
