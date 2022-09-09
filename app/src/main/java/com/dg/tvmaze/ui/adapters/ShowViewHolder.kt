package com.dg.tvmaze.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.ShowItemBinding
import com.dg.tvmaze.entities.Show

class ShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup) =
            ShowViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false))
    }

    private val binding = ShowItemBinding.bind(itemView)

    fun bind(show: Show?) {
        binding.titleTextView.text = show?.name?: ""
    }

}
