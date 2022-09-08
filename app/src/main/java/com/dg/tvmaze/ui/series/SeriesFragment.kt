package com.dg.tvmaze.ui.series

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentSeriesBinding
import com.dg.tvmaze.ui.episode.EpisodeFragment
import com.dg.tvmaze.ui.episode.newInstance

class SeriesFragment : Fragment(R.layout.fragment_series) {

    companion object;
    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var series: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        series = requireArguments().getString(seriesKey)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSeriesBinding.bind(view)

        binding.seriesTextView.text = "Series $series"
        binding.goToEpisodeButton.setOnClickListener {
            val episodeFragment = EpisodeFragment.newInstance(series, "")
            parentFragmentManager.commit {
                add(R.id.fragmentContainerView, episodeFragment)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}


private const val seriesKey = "series"

fun SeriesFragment.Companion.newInstance(series: String) = SeriesFragment().apply {
    arguments = Bundle().apply { putString(seriesKey, series) }
}






