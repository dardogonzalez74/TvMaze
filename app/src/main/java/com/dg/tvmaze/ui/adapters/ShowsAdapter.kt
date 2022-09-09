package com.dg.tvmaze.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.dg.tvmaze.entities.Show

class ShowsAdapter : PagingDataAdapter<Show, ShowViewHolder>(COMPARATOR) {

    private var onShowClicked: ((Show) -> Unit)? = null

    fun setOnShowClickedListener(onShowClicked: ((Show) -> Unit)) {
        this.onShowClicked = onShowClicked
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Show>() {
            override fun areContentsTheSame(oldItem: Show, newItem: Show) = oldItem == newItem
            override fun areItemsTheSame(oldItem: Show, newItem: Show) = oldItem.id == newItem.id
            override fun getChangePayload(oldItem: Show, newItem: Show): Any? = null
        }
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int, payloads: MutableList<Any>) {
        onBindViewHolder(holder, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder.create(parent, onShowClicked)
    }

}
