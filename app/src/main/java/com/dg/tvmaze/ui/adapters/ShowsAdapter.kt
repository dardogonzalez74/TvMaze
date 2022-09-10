package com.dg.tvmaze.ui.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dg.tvmaze.entities.Show

class ShowsAdapter : RecyclerView.Adapter<ShowViewHolder>() {

    var shows: List<Show> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set (value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ShowViewHolder.create(parent)

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    override fun getItemCount() = shows.size
}