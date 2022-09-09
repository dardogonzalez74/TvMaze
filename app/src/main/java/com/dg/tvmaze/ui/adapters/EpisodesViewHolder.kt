package com.dg.tvmaze.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.EpisodeViewBinding
import com.dg.tvmaze.entities.Episode

class EpisodesViewHolder(
    view: View,
    private val onClicked: ((Episode) -> Unit)? = null
): RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup, onClicked: ((Episode) -> Unit)? = null) =
            EpisodesViewHolder(
                view = LayoutInflater.from(parent.context).inflate(R.layout.episode_view, parent, false),
                onClicked = onClicked
            )
    }

    private val binding = EpisodeViewBinding.bind(itemView)

    fun bind(episode: Episode) {
        binding.titleTextView.text = episode.name
        Glide.with(itemView.context)
            //TODO just for testing, remove this
            .setDefaultRequestOptions(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .load(episode.thumbnail)
            .placeholder(R.drawable.ic_image_loading)
            .error(R.drawable.ic_image_error)
            .into(binding.thumbnailImageView)

//        binding.seasonTextView.text = context.getString(R.string.season_number, season)
    }
}

