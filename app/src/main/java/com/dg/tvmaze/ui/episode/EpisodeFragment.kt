package com.dg.tvmaze.ui.episode

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentEpisodeBinding
import com.dg.tvmaze.entities.Episode
import com.dg.tvmaze.entities.Show

class EpisodeFragment : Fragment(R.layout.fragment_episode) {

    companion object;
    private var _binding: FragmentEpisodeBinding? = null
    private val binding get() = _binding!!

    private lateinit var show: Show
    private lateinit var episode: Episode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        show = requireArguments().getParcelable(showKey)!!
        episode = requireArguments().getParcelable(episodeKey)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEpisodeBinding.bind(view)
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
    }

    private fun showDetails() {
        binding.showNameTextView.text = show.name
        binding.episodeNameTextView.text = episode.name
        binding.summaryTextView.text = Html.fromHtml(episode.summary, Html.FROM_HTML_MODE_COMPACT)
        binding.episodeNumberTextView.text = "${episode.number}"
        binding.seasonNumberTextView.text = "${episode.season}"

        episode.image
            ?.let {
                Glide.with(requireContext())
                    //TODO just for testing, remove this
                    .setDefaultRequestOptions(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .load(it)
                    .into(binding.coverImageView)
            }
            ?:run { binding.coverImageView.isVisible = false }

    }

}


private const val showKey = "show"
private const val episodeKey = "episode"

fun EpisodeFragment.Companion.newInstance(show: Show, episode: Episode) = EpisodeFragment().apply {
    arguments = Bundle().apply {
        putParcelable(showKey, show)
        putParcelable(episodeKey, episode)
    }
}
