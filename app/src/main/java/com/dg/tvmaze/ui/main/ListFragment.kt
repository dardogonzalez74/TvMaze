package com.dg.tvmaze.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentListBinding
import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.ui.adapters.ShowsAdapter
import com.dg.tvmaze.ui.adapters.ShowsLoadStateAdapter
import com.dg.tvmaze.ui.series.ShowDetailFragment
import com.dg.tvmaze.ui.series.newInstance
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BottomNavigationFragment(R.layout.fragment_list) {

    companion object;
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by sharedViewModel<MainViewModel>()
    private val adapter by lazy { ShowsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)
        setupAdapter()
    }

    private fun setupAdapter() {
        makeVisible(binding.loadingLinearLayout)
        adapter.onShowClicked = ::showDetails
        binding.retryTextView.setOnClickListener { adapter.retry() }

        binding.showsRecyclerView.adapter =
            adapter.withLoadStateFooter(ShowsLoadStateAdapter(adapter))

        lifecycleScope.launchWhenCreated {
            viewModel.shows.collectLatest { adapter.submitData(it) }
        }

        adapter.addLoadStateListener { loadState ->
            when (loadState.refresh) {
                is LoadState.NotLoading -> makeVisible(binding.showsRecyclerView)
                is LoadState.Loading -> makeVisible(binding.loadingLinearLayout)
                is LoadState.Error -> makeVisible(binding.errorLinearLayout)
            }
        }
    }

    private fun showDetails(show: Show) {
        val showDetailFragment = ShowDetailFragment.newInstance(show)
        childFragmentManager.commit {
            add(R.id.fragmentContainerView, showDetailFragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun makeVisible(view: View) {
        binding.loadingLinearLayout.isVisible = view == binding.loadingLinearLayout
        binding.errorLinearLayout.isVisible = view == binding.errorLinearLayout
        binding.showsRecyclerView.isVisible = view == binding.showsRecyclerView
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

fun ListFragment.Companion.newInstance() = ListFragment()