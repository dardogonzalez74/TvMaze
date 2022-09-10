package com.dg.tvmaze.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dg.tvmaze.entities.Episode
import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.extensions.asLiveData
import com.dg.tvmaze.usecases.RetrieveShowsPagedUseCase
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val retrieveShowsPagedUC: RetrieveShowsPagedUseCase
): ViewModel() {

    private val favorites = arrayListOf<Show>()
    private val _favoritesLiveData = MutableLiveData<List<Show>>()
    val favoritesLiveData = _favoritesLiveData.asLiveData()

    init {
        log("init MainViewModel")
    }

    fun toggleFavorite(show: Show) {
        favorites.find { it.id == show.id }
            ?.run { favorites.remove(show) }
            ?:run { favorites.add(show)}
        _favoritesLiveData.value = favorites
    }

    val showsFlow: Flow<PagingData<Show>> =
        Pager(
            config = PagingConfig(
                pageSize = 250,
                prefetchDistance = 2
            ),
            pagingSourceFactory = { retrieveShowsPagedUC.getDataSource() }
        ).flow.cachedIn(viewModelScope)

}