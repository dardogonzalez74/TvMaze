package com.dg.tvmaze.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentFavoritesBinding
import com.dg.tvmaze.databinding.FragmentSearchBinding
import com.dg.tvmaze.ui.main.BottomNavigationFragment
import com.dg.tvmaze.ui.search.SearchFragment
import com.dg.tvmaze.ui.series.SeriesFragment
import com.dg.tvmaze.ui.series.newInstance

class FavoritesFragment : BottomNavigationFragment(R.layout.fragment_favorites) {

    companion object;
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)

        binding.goToSeriesButton.setOnClickListener {
            val seriesFragment = SeriesFragment.newInstance("From Favorites")
            childFragmentManager.commit {
                add(R.id.fragmentContainerView, seriesFragment)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

fun FavoritesFragment.Companion.newInstance() = FavoritesFragment()