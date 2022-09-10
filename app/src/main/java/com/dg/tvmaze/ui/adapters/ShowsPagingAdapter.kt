package com.dg.tvmaze.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.dg.tvmaze.entities.Show

class ShowsPagingAdapter : PagingDataAdapter<Show, ShowViewHolder>(COMPARATOR) {

    var onShowClicked: ((Show) -> Unit)? = null
    var onFavoriteClicked: ((Show) -> Unit)? = null
    var favorites: List<Show> = listOf()
        set(value) {
            field = value
            processFavorites()
        }

    private fun processFavorites() {
        val oldAll = snapshot().items.mapIndexed { index, show -> Triple(index, show.id, show.favorite)  }
        val oldFavorites = oldAll.filter { it.third }
        val oldNotFavorites = oldAll.filterNot { it.third }
        val removed = oldFavorites.filter { old -> favorites.none { fav -> fav.id == old.second } }
        val added = oldNotFavorites.filter { old -> favorites.any { fav -> fav.id == old.second } }
        removed.forEach {
            getItem(it.first)?.favorite = false
            notifyItemChanged(it.first)
        }
        added.forEach {
            getItem(it.first)?.favorite = true
            notifyItemChanged(it.first)
        }
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
        return ShowViewHolder.create(parent, onShowClicked, onFavoriteClicked)
    }

}
