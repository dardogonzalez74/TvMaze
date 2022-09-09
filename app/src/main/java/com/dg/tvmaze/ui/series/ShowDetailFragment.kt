package com.dg.tvmaze.ui.series

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dg.tvmaze.R
import com.dg.tvmaze.databinding.FragmentShowDetailsBinding
import com.dg.tvmaze.entities.Show

class ShowDetailFragment : Fragment(R.layout.fragment_show_details) {

    companion object;
    private var _binding: FragmentShowDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var show: Show

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        show = requireArguments().getParcelable(showKey)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShowDetailsBinding.bind(view)
        setListeners()
        showDetails()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun showDetails() {
        binding.nameTextView.text = show.name
        binding.summaryTextView.text = Html.fromHtml(show.summary, Html.FROM_HTML_MODE_COMPACT)
        Glide.with(requireContext())
            //TODO just for testing, remove this
            .setDefaultRequestOptions(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .load(show.image)
            .into(binding.coverImageView);
    }

    private fun setListeners() {
        binding.backImageView.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

}


private const val showKey = "show"

fun ShowDetailFragment.Companion.newInstance(show: Show) = ShowDetailFragment().apply {
    arguments = Bundle().apply { putParcelable(showKey, show) }
}






