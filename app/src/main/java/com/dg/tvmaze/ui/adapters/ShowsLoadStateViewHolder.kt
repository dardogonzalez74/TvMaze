package com.dg.tvmaze.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.ShowLoadStateItemBinding

class ShowsLoadStateViewHolder(
    parent: ViewGroup,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.show_load_state_item, parent, false)
) {
    private val binding = ShowLoadStateItemBinding.bind(itemView)

    init {
        binding.retryButton.setOnClickListener { retryCallback() }
    }

    fun bindTo(loadState: LoadState) {
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
    }
}
