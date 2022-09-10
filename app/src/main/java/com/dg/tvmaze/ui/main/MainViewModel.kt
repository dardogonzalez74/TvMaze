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
import com.dg.tvmaze.extensions.wrap
import com.dg.tvmaze.usecases.RetrieveFavoritesUseCase
import com.dg.tvmaze.usecases.RetrieveShowsBySearchUseCase
import com.dg.tvmaze.usecases.RetrieveShowsPagedUseCase
import com.dg.tvmaze.usecases.UpdateFavoritesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainViewModel(
    private val retrieveFavoritesUC: RetrieveFavoritesUseCase,
    private val updateFavoritesUC: UpdateFavoritesUseCase,
    private val retrieveShowsPagedUC: RetrieveShowsPagedUseCase,
    private val retrieveShowsBySearchUC: RetrieveShowsBySearchUseCase
): ViewModel() {

    private val favorites = arrayListOf<Show>()
    private val _favoritesLiveData = MutableLiveData<List<Show>>()
    val favoritesLiveData = _favoritesLiveData.asLiveData()

    private var searchShowsJob: Job? = null
    private val _searchLiveData = MutableLiveData<Result<List<Show>>>()
    val searchLiveData = _searchLiveData.asLiveData()

    init {
        viewModelScope.launch {
            wrap { retrieveFavoritesUC.getAll() }.getOrNull()?.let {
                favorites.addAll(it)
                _favoritesLiveData.value = favorites
            }
        }
    }

    fun toggleFavorite(show: Show) {
        favorites.find { it.id == show.id }
            ?.let {
                favorites.remove(it)
                updateFavoritesUC.remove(show)
            }
            ?:run {
                favorites.add(show.copy(favorite = true))
                updateFavoritesUC.add(show)
            }
        _favoritesLiveData.value = favorites
    }

    fun removeFavorite(show: Show) {
        favorites.find { it.id == show.id }?.let {
            favorites.remove(it)
            updateFavoritesUC.remove(it)
            _favoritesLiveData.value = favorites
        }
    }

    val showsFlow: Flow<PagingData<Show>> =
        Pager(
            config = PagingConfig(
                pageSize = 250,
                prefetchDistance = 2
            ),
            pagingSourceFactory = { retrieveShowsPagedUC.getDataSource() }
        ).flow.cachedIn(viewModelScope)

    fun searchShows(query: String) {
        if(query.length < 3) return
        searchShowsJob?.cancel()
        searchShowsJob = viewModelScope.launch {
            // Adding a delay just to not start a lot of
            // queries when the user is typing fast
            delay(500)
            if(!isActive) return@launch
            val shows = wrap { retrieveShowsBySearchUC.search(query) }
            if(isActive) _searchLiveData.value = shows
        }
    }

}