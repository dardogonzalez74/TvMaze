package com.dg.tvmaze.ui.episode

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentEpisodeBinding

class EpisodeFragment : Fragment(R.layout.fragment_episode) {

    companion object;
    private var _binding: FragmentEpisodeBinding? = null
    private val binding get() = _binding!!

    private lateinit var series: String
    private lateinit var episode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        series = requireArguments().getString(seriesKey)!!
        episode = requireArguments().getString(episodeKey)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEpisodeBinding.bind(view)

        binding.episodeTextView.text = "Episode $series"
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}


private const val seriesKey = "series"
private const val episodeKey = "episode"

fun EpisodeFragment.Companion.newInstance(series: String, episode: String) = EpisodeFragment().apply {
    arguments = Bundle().apply {
        putString(seriesKey, series)
        putString(episodeKey, episode)
    }
}
