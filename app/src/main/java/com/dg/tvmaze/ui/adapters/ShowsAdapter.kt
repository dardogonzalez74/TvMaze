package com.dg.tvmaze.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.dg.tvmaze.entities.Show

class ShowsAdapter : PagingDataAdapter<Show, ShowViewHolder>(COMPARATOR) {

    var onShowClicked: ((Show) -> Unit)? = null
    var onFavoriteClicked: ((Show) -> Unit)? = null
    var favorites: List<Show> = listOf()
        set(value) {
            field = value
            processFavorites()
        }

    private fun processFavorites() {
        val oldIndexed = snapshot().items.mapIndexed { index, show -> Pair(index, show)  }
        val oldIndexedFavorites = oldIndexed.filter { it.second.favorite }
        val removed = oldIndexedFavorites.filterNot { old -> favorites.map { it.id }.contains(old.second.id) }
        val added = favorites.filterNot { fav -> oldIndexedFavorites.map { it.second.id }.contains(fav.id) }
        removed.forEach {
            it.second.favorite = false
            notifyItemChanged(it.first)
        }
        oldIndexed.filter { added.map { it.id }.contains(it.second.id) }.forEach {
            it.second.favorite = true
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
