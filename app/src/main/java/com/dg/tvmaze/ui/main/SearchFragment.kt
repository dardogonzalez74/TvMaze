package com.dg.tvmaze.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentSearchBinding
import com.dg.tvmaze.ui.main.BottomNavigationFragment
import com.dg.tvmaze.ui.series.ShowDetailFragment
import com.dg.tvmaze.ui.series.newInstance


class SearchFragment : BottomNavigationFragment(R.layout.fragment_search) {

    companion object;
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        binding.goToSeriesButton.setOnClickListener {
//            val showDetailFragment = ShowDetailFragment.newInstance("From Search")
//            childFragmentManager.commit {
//                add(R.id.fragmentContainerView, showDetailFragment)
//                setReorderingAllowed(true)
//                addToBackStack(null)
//            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

fun SearchFragment.Companion.newInstance() = SearchFragment()