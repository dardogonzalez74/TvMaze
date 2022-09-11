package com.dg.tvmaze.ui.shows

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentShowDetailsBinding
import com.dg.tvmaze.entities.Episode
import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.ui.adapters.EpisodesAdapter
import com.dg.tvmaze.ui.episode.EpisodeFragment
import com.dg.tvmaze.ui.episode.newInstance
import com.google.android.material.tabs.TabLayout
import org.koin.android.ext.android.inject

class ShowDetailFragment : Fragment(R.layout.fragment_show_details) {

    companion object;
    private var _binding: FragmentShowDetailsBinding? = null
    private val binding get() = _binding!!
    private val unknown by lazy { requireContext().getString(R.string.not_available) }
    private val viewModel: ShowDetailsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initWith(show = requireArguments().getParcelable(showKey)!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShowDetailsBinding.bind(view)
        setListeners()
        showDetails()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setListeners() {
        binding.backImageView.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        viewModel.episodes.observe(viewLifecycleOwner) { showEpisodes(it) }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.scrollView.isVisible = tab.position == 0
                binding.episodesLinearLayout.isVisible = tab.position == 1
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun showDetails() {
        val show = viewModel.show
        binding.nameTextView.text = show.name
        show.summary?.let { summary ->
            binding.summaryTextView.text = Html.fromHtml(summary, Html.FROM_HTML_MODE_COMPACT)
        }
        binding.genreTextView.text = show.genres?: unknown
        binding.languageTextView.text = show.language?: unknown
        binding.ratingTextView.text = "${show.rating?: unknown}"
        binding.daysTextView.text = show.days?: unknown
        binding.timeTextView.text = show.time?: unknown
        binding.durationTextView.text = getString(R.string.duration_info, show.runtime?: unknown)
        binding.premieredTextView.text = show.premiered?: unknown
        binding.endedTextView.text = show.ended?: "-"

        Glide.with(requireContext())
            .load(show.image)
            .into(binding.coverImageView)
    }

    private fun showEpisodes(result: Result<List<Episode>>) {
        binding.episodesProgressBar.isVisible = false
        result.getOrNull()
            ?.let { showEpisodes(it) }
            ?:run { binding.episodesErrorTextView.isVisible = true }
    }

    private fun showEpisodes(episodes: List<Episode>) {
        val adapter = EpisodesAdapter()
        adapter.onEpisodeClicked = ::showDetails
        adapter.episodes = episodes
        binding.episodesRecyclerView.adapter = adapter
    }

    private fun showDetails(episode: Episode) {
        val episodeFragment = EpisodeFragment.newInstance(viewModel.show, episode)
        parentFragmentManager.commit {
            add(R.id.fragmentContainerView, episodeFragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}


private const val showKey = "show"

fun ShowDetailFragment.Companion.newInstance(show: Show) = ShowDetailFragment().apply {
    arguments = Bundle().apply { putParcelable(showKey, show) }
}






