package com.dg.tvmaze.ui.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dg.tvmaze.entities.Episode

class EpisodesAdapter : RecyclerView.Adapter<EpisodesViewHolder>() {

    var onEpisodeClicked: ((Episode) -> Unit)? = null

    var episodes: List<Episode> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set (value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EpisodesViewHolder.create(parent, onEpisodeClicked)

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount() = episodes.size
}