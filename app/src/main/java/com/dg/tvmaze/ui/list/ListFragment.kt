package com.dg.tvmaze.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentListBinding
import com.dg.tvmaze.ui.adapters.ShowsAdapter
import com.dg.tvmaze.ui.adapters.ShowsLoadStateAdapter
import com.dg.tvmaze.ui.main.BottomNavigationFragment
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
        binding.showsRecyclerView.adapter =
            adapter.withLoadStateFooter(ShowsLoadStateAdapter(adapter))

        lifecycleScope.launchWhenCreated {
            viewModel.shows.collectLatest { adapter.submitData(it) }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

fun ListFragment.Companion.newInstance() = ListFragment()