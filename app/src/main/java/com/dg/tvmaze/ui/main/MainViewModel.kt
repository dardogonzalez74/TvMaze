package com.dg.tvmaze.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.usecases.RetrieveShowsPagedUseCase
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val retrieveShowsPagedUC: RetrieveShowsPagedUseCase
): ViewModel() {

    init {
        log("init MainViewModel")
    }

    val shows: Flow<PagingData<Show>> =
        Pager(
            config = PagingConfig(
                pageSize = 250,
                prefetchDistance = 2
            ),
            pagingSourceFactory = { retrieveShowsPagedUC.getDataSource() }
        ).flow.cachedIn(viewModelScope)

}