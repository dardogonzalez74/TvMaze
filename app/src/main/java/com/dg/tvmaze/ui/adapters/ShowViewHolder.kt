package com.dg.tvmaze.ui.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.ShowItemBinding
import com.dg.tvmaze.entities.Show

class ShowViewHolder(
    view: View,
    private val onClicked: ((Show) -> Unit)? = null,
    private val onFavorited: ((Show) -> Unit)? = null
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup, onClicked: ((Show) -> Unit)? = null, onFavorited: ((Show) -> Unit)? = null) =
            ShowViewHolder(
                view = LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false),
                onClicked = onClicked,
                onFavorited = onFavorited
            )
    }

    private val binding = ShowItemBinding.bind(itemView)

    fun bind(show: Show?) {
        show
            ?.let {
                itemView.setOnClickListener { onClicked?.invoke(show) }
                binding.favoriteImageView.setOnClickListener { onFavorited?.invoke(show) }
                binding.titleTextView.text = it.name
                binding.summaryTextView.text = Html.fromHtml(it.summary,Html.FROM_HTML_MODE_COMPACT)
                binding.propertyOfTextView.text = it.propertyOf
                if(it.favorite) {
                    binding.favoriteImageView.setImageResource(R.drawable.ic_favorite)
                    binding.favoriteImageView.setColorFilter(ContextCompat.getColor(itemView.context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN)
                } else {
                    binding.favoriteImageView.setImageResource(R.drawable.ic_favorite_border)
                    binding.favoriteImageView.setColorFilter(ContextCompat.getColor(itemView.context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
                }
                Glide.with(itemView.context)

                    //TODO just for testing, remove this
                    .setDefaultRequestOptions(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))

                    .load(it.thumbnail)
                    .placeholder(R.drawable.ic_image_loading)
                    .error(R.drawable.ic_image_error)
                    .into(binding.thumbnailImageView);
            }
            ?:run {
                itemView.setOnClickListener {  }
                binding.favoriteImageView.setOnClickListener {  }
                binding.titleTextView.text = ""
                binding.propertyOfTextView.text = ""
                binding.summaryTextView.text = ""
                binding.favoriteImageView.setImageResource(R.drawable.ic_favorite_border)
                binding.thumbnailImageView.setImageResource(R.drawable.ic_image_error)
            }
    }

}
