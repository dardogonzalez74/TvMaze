package com.dg.tvmaze.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentListBinding
import com.dg.tvmaze.ui.main.BottomNavigationFragment
import com.dg.tvmaze.ui.series.SeriesFragment
import com.dg.tvmaze.ui.series.newInstance
import org.koin.android.ext.android.inject

class ListFragment : BottomNavigationFragment(R.layout.fragment_list) {

    companion object;
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

        binding.goToSeriesButton.setOnClickListener {
            val seriesFragment = SeriesFragment.newInstance("From List")
            childFragmentManager.commit {
                add(R.id.fragmentContainerView, seriesFragment)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.name.observe(this) { binding.idTextView.text = it }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

fun ListFragment.Companion.newInstance() = ListFragment()