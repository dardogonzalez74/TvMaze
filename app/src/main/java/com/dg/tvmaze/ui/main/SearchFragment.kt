package com.dg.tvmaze.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SearchFragment : BottomNavigationFragment(R.layout.fragment_search) {

    companion object;
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        lifecycleScope.launchWhenCreated {
            viewModel.showsFlow.collectLatest {  }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

fun SearchFragment.Companion.newInstance() = SearchFragment()