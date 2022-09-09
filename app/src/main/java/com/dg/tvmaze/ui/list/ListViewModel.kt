package com.dg.tvmaze.ui.list

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.extensions.asLiveData
import com.dg.tvmaze.extensions.wrap
import com.dg.tvmaze.usecases.RetrieveShowUseCase
import com.dg.tvmaze.usecases.RetrieveShowsPagedUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@SuppressLint("NullSafeMutableLiveData") // Lint is not processing well <T:Any> from wrap
class ListViewModel(
    private val retrieveShowUC: RetrieveShowUseCase,
    private val retrieveShowsPagedUC: RetrieveShowsPagedUseCase
): ViewModel() {

    private val _show = MutableLiveData<Result<Show>>()
    val show = _show.asLiveData()

    fun getShow(id: Int) {
        viewModelScope.launch {
            _show.value = wrap { retrieveShowUC.byId(id) }
        }
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