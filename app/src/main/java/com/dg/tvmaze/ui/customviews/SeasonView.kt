package com.dg.tvmaze.ui.customviews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.*
import com.dg.tvmaze.entities.Episode

class SeasonView(
    private val context: Context,
    private val season: Int,
    private val episodes: List<Episode>
) {

    private val inflater by lazy { LayoutInflater.from(context)!! }
    private val unknown by lazy { context.getString(R.string.not_available) }

    fun createView(): View {
        val seasonView = inflater.inflate(R.layout.season_view, null)
        val binding = SeasonViewBinding.bind(seasonView)
        binding.seasonTextView.text = context.getString(R.string.season_number, season)
        episodes.forEach { episode ->
        }
        return seasonView
    }
}

