package com.dg.tvmaze.repositories

import com.dg.tvmaze.database.ShowDao
import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.extensions.parse
import com.dg.tvmaze.network.SearchEndpoint
import com.dg.tvmaze.network.ShowEndpoint
import com.dg.tvmaze.network.entities.toAppModel

class ShowRepository(
    private val showDao: ShowDao,
    private val showEndpoint: ShowEndpoint,
    private val searchEndpoint: SearchEndpoint
) {

    suspend fun fromNetworkSearch(query: String): List<Show> =
        searchEndpoint.searchShows(query).parse().map { it.show.toAppModel() }

    suspend fun fromNetworkGetByPage(page: Int): List<Show> =
        showEndpoint.getByPage(page).parse().map { it.toAppModel() }

    suspend fun fromDbInsert(show: Show) {
        showDao.insertWithReplace(show)
    }

    suspend fun fromDbDelete(show: Show) {
        showDao.delete(show)
    }

    suspend fun fromDbGetAll(): List<Show> = showDao.getAll()

}

