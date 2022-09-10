package com.dg.tvmaze.ui.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dg.tvmaze.entities.Show

class ShowsAdapter : RecyclerView.Adapter<ShowViewHolder>() {

    var onShowClicked: ((Show) -> Unit)? = null
    var onFavoriteClicked: ((Show) -> Unit)? = null
    var onRemoveClicked: ((Show) -> Unit)? = null
    var favorites: List<Show> = listOf()
        set(value) {
            field = value
            processFavorites()
        }
    var shows: List<Show> = emptyList()
        set (value) {
            field = value
            processShows()
        }

    @SuppressLint("NotifyDataSetChanged")
    private fun processShows() {
        onFavoriteClicked?.let {
            shows.forEach { show -> show.favorite = favorites.any { show.id == it.id } }
        }
        notifyDataSetChanged()
    }

    private fun processFavorites() {
        onFavoriteClicked?: return
        val oldAll = shows.mapIndexed { index, show -> Triple(index, show.id, show.favorite)  }
        val oldFavorites = oldAll.filter { it.third }
        val oldNotFavorites = oldAll.filterNot { it.third }
        val removed = oldFavorites.filter { old -> favorites.none { fav -> fav.id == old.second } }
        val added = oldNotFavorites.filter { old -> favorites.any { fav -> fav.id == old.second } }
        removed.forEach {
            shows[it.first].favorite = false
            notifyItemChanged(it.first)
        }
        added.forEach {
            shows[it.first].favorite = true
            notifyItemChanged(it.first)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ShowViewHolder.create(parent, onShowClicked, onFavoriteClicked, onRemoveClicked)

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    override fun getItemCount() = shows.size
}