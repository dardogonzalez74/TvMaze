package com.dg.tvmaze.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.commit
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentSearchBinding
import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.ui.adapters.ShowsAdapter
import com.dg.tvmaze.ui.shows.ShowDetailFragment
import com.dg.tvmaze.ui.shows.newInstance
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SearchFragment : BottomNavigationFragment(R.layout.fragment_search) {

    companion object;
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by sharedViewModel<MainViewModel>()
    private val adapter by lazy { ShowsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)
        setListeners()
        initView()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setListeners() {
        binding.showsSearchView.setOnQueryTextListener(queryTextListener)
        viewModel.searchLiveData.observe(viewLifecycleOwner) { result->
            result.getOrNull()
                ?.let { adapter.shows = it }
                ?:run { Toast.makeText(requireContext(), R.string.search_error, Toast.LENGTH_SHORT).show() }
        }
        viewModel.favoritesLiveData.observe(viewLifecycleOwner) {
            adapter.favorites = it
        }
    }

    private fun initView() {
        binding.searchRecyclerView.adapter = adapter
        adapter.onShowClicked = ::showDetails
        adapter.onFavoriteClicked = ::toggleFavorite
    }

    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?) = false
        override fun onQueryTextChange(newText: String?): Boolean {
            newText?.let { viewModel.searchShows(it) }
            return false
        }
    }

    private fun toggleFavorite(show: Show) {
        viewModel.toggleFavorite(show)
    }

    private fun showDetails(show: Show) {
        val showDetailFragment = ShowDetailFragment.newInstance(show)
        childFragmentManager.commit {
            add(R.id.fragmentContainerView, showDetailFragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

}

fun SearchFragment.Companion.newInstance() = SearchFragment()