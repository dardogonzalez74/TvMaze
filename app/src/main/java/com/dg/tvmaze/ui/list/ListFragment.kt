package com.dg.tvmaze.ui.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentListBinding
import com.dg.tvmaze.ui.adapters.ShowsAdapter
import com.dg.tvmaze.ui.adapters.ShowsLoadStateAdapter
import com.dg.tvmaze.ui.main.BottomNavigationFragment
import com.dg.tvmaze.ui.main.log
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject

class ListFragment : BottomNavigationFragment(R.layout.fragment_list) {

    companion object;
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by inject()
    private val adapter by lazy { ShowsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)
        setupAdapter()
    }

    private fun setupAdapter() {
        makeVisible(binding.loadingLinearLayout)

        binding.retryTextView.setOnClickListener {
            adapter.retry()
        }

        binding.showsRecyclerView.adapter =
            adapter.withLoadStateFooter(ShowsLoadStateAdapter(adapter))

        lifecycleScope.launchWhenCreated {
            viewModel.shows.collectLatest { adapter.submitData(it) }
        }

        adapter.addLoadStateListener { loadState ->
            log("running ${loadState.refresh}")
            when (loadState.refresh) {
                is LoadState.NotLoading -> makeVisible(binding.showsRecyclerView)
                is LoadState.Loading -> makeVisible(binding.loadingLinearLayout)
                is LoadState.Error -> makeVisible(binding.errorLinearLayout)
            }
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