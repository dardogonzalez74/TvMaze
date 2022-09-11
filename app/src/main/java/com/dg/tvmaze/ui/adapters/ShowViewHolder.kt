package com.dg.tvmaze.ui.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.ShowItemBinding
import com.dg.tvmaze.entities.Show

class ShowViewHolder(
    view: View,
    private val onClicked: ((Show) -> Unit)? = null,
    private val onFavorite: ((Show) -> Unit)? = null,
    private var onRemove: ((Show) -> Unit)? = null
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(
            parent: ViewGroup,
            onClicked: ((Show) -> Unit)? = null,
            onFavorite: ((Show) -> Unit)? = null,
            onRemove: ((Show) -> Unit)? = null) =
                ShowViewHolder(
                    view = LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false),
                    onClicked = onClicked,
                    onFavorite = onFavorite,
                    onRemove = onRemove
                )
    }

    private val binding = ShowItemBinding.bind(itemView)

    init {
        binding.favoriteImageView.isVisible = onFavorite != null
        binding.deleteImageView.isVisible = onRemove != null
    }

    fun bind(show: Show?) {
        show
            ?.let {
                itemView.setOnClickListener { _ -> onClicked?.invoke(it) }
                bindFavorite(it)
                bindRemove(it)

                binding.titleTextView.text = it.name
                it.summary?.let { summary ->
                    binding.summaryTextView.text = Html.fromHtml(summary, Html.FROM_HTML_MODE_COMPACT)
                }
                binding.propertyOfTextView.text = it.propertyOf
                Glide.with(itemView.context)
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

    private fun bindRemove(show: Show) {
        onRemove?: return
        binding.deleteImageView.setOnClickListener { onRemove?.invoke(show) }
    }

    private fun bindFavorite(show: Show) {
        onFavorite?: return
        binding.favoriteImageView.setOnClickListener { onFavorite.invoke(show) }
        if(show.favorite) {
            binding.favoriteImageView.setImageResource(R.drawable.ic_favorite)
            binding.favoriteImageView.setColorFilter(ContextCompat.getColor(itemView.context, R.color.yellow), android.graphics.PorterDuff.Mode.SRC_IN)
        } else {
            binding.favoriteImageView.setImageResource(R.drawable.ic_favorite_border)
            binding.favoriteImageView.setColorFilter(ContextCompat.getColor(itemView.context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        }
    }

}
