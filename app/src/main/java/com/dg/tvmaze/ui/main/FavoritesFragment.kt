package com.dg.tvmaze.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentFavoritesBinding
import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.ui.adapters.ShowsAdapter
import com.dg.tvmaze.ui.shows.ShowDetailFragment
import com.dg.tvmaze.ui.shows.newInstance
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoritesFragment : BottomNavigationFragment(R.layout.fragment_favorites) {

    companion object;
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by sharedViewModel<MainViewModel>()
    private val adapter by lazy { ShowsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)
        setListeners()
        initView()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setListeners() {
        viewModel.favoritesLiveData.observe(viewLifecycleOwner) {
            adapter.shows = it.sortedBy { it.name }
            binding.favoritesRecyclerView.isVisible = it.isNotEmpty()
            binding.emptyLinearLayout.isVisible = it.isEmpty()
        }
    }

    private fun initView() {
        binding.favoritesRecyclerView.adapter = adapter
        adapter.onShowClicked = ::showDetails
        adapter.onRemoveClicked = ::removeFavorite
    }

    private fun showDetails(show: Show) {
        val showDetailFragment = ShowDetailFragment.newInstance(show)
        childFragmentManager.commit {
            add(R.id.fragmentContainerView, showDetailFragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun removeFavorite(show: Show) {
        viewModel.removeFavorite(show)
    }
}

fun FavoritesFragment.Companion.newInstance() = FavoritesFragment()