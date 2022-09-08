package com.dg.tvmaze.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentListBinding
import com.dg.tvmaze.ui.main.BottomNavigationFragment
import com.dg.tvmaze.ui.series.SeriesFragment
import com.dg.tvmaze.ui.series.newInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.fragmentScope

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

        viewModel.show.observe(viewLifecycleOwner) {
            binding.idTextView.text = it.getOrNull()?.name?: "Error"
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getShow(4)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

fun ListFragment.Companion.newInstance() = ListFragment()