package com.dg.tvmaze.ui.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class ShowsLoadStateAdapter(
    private val adapter: ShowsAdapter
) : LoadStateAdapter<ShowsLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: ShowsLoadStateViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ShowsLoadStateViewHolder {
        return ShowsLoadStateViewHolder(parent) { adapter.retry() }
    }
}