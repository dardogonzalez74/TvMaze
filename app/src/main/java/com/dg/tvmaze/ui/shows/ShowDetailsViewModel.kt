package com.dg.tvmaze.ui.shows

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dg.tvmaze.entities.Episode
import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.extensions.asLiveData
import com.dg.tvmaze.extensions.wrap
import com.dg.tvmaze.usecases.IRetrieveEpisodesUseCase
import kotlinx.coroutines.launch

@SuppressLint("NullSafeMutableLiveData") // Lint is not processing well <T:Any> from wrap
class ShowDetailsViewModel(
    private val retrieveEpisodesUseCase: IRetrieveEpisodesUseCase
): ViewModel() {

    private val _episodes = MutableLiveData<Result<List<Episode>>>()
    val episodes = _episodes.asLiveData()

    lateinit var show: Show
        private set

    fun initWith(show: Show) {
        this.show = show
        viewModelScope.launch {
            _episodes.value = wrap { retrieveEpisodesUseCase.byShowId(show.id) }
        }
    }


}