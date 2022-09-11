package com.dg.tvmaze.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.EpisodeItemBinding
import com.dg.tvmaze.entities.Episode

class EpisodesViewHolder(
    view: View,
    private val onClicked: ((Episode) -> Unit)? = null
): RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup, onClicked: ((Episode) -> Unit)? = null) =
            EpisodesViewHolder(
                view = LayoutInflater.from(parent.context).inflate(R.layout.episode_item, parent, false),
                onClicked = onClicked
            )
    }

    private val binding = EpisodeItemBinding.bind(itemView)

    fun bind(episode: Episode) {
        itemView.setOnClickListener { onClicked?.invoke(episode) }
        binding.seasonCardView.isVisible = episode.number == 1
        binding.seasonSeparatorView.isVisible = episode.number == 1 && episode.season != 1
        binding.seasonTextView.text = itemView.context.getString(R.string.season_number, episode.season)
        binding.episodeTextView.text = itemView.context.getString(R.string.episode_number, episode.number)
        binding.nameTextView.text = episode.name
        Glide.with(itemView.context)
            .load(episode.thumbnail)
            .placeholder(R.drawable.ic_image_loading)
            .error(R.drawable.ic_image_error)
            .into(binding.thumbnailImageView)
    }
}

