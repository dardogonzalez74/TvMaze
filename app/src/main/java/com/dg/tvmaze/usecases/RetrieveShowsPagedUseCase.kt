package com.dg.tvmaze.usecases

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.repositories.ShowRepository
import org.koin.core.component.KoinComponent

class RetrieveShowsPagedUseCase(
    private val showRepository: ShowRepository,
    private val retrieveFavoritesUC: RetrieveFavoritesUseCase
) {

    fun getDataSource() = ShowsPagingDataSource(showRepository, retrieveFavoritesUC)

    class ShowsPagingDataSource(
        private val showRepository: ShowRepository,
        private val retrieveFavoritesUC: RetrieveFavoritesUseCase
    ) : PagingSource<Int, Show>(), KoinComponent {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {
            val pageNumber = params.key ?: 0
            return try {
                val response = showRepository.fromNetworkGetByPage(pageNumber)
                val favorites = retrieveFavoritesUC.getAll()
                response.forEach { it.favorite = favorites.any { fav -> fav.id == it.id } }
                LoadResult.Page(
                    data = response,
                    prevKey = null,
                    nextKey = if(response.isNotEmpty()) pageNumber + 1 else null
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Show>): Int? = null
    }
}