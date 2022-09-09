package com.dg.tvmaze.ui.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
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
        show
            ?.let {
                binding.titleTextView.text = it.name
                binding.summaryTextView.text = Html.fromHtml(it.summary,Html.FROM_HTML_MODE_COMPACT)
                binding.propertyOfTextView.text = it.propertyOf
                Glide.with(itemView.context)

                    //TODO just for testing, remove this
                    .setDefaultRequestOptions(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))

                    .load(it.thumbnail)
                    .placeholder(R.drawable.ic_image_loading)
                    .error(R.drawable.ic_image_error)
                    .into(binding.thumbnailImageView);
            }
            ?:run {
                binding.titleTextView.text = ""
                binding.propertyOfTextView.text = ""
                binding.summaryTextView.text = ""
                binding.thumbnailImageView.setImageResource(R.drawable.ic_image_error)
            }
    }

}
