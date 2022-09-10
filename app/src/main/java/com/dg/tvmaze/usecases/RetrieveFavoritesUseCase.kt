package com.dg.tvmaze.usecases

import com.dg.tvmaze.entities.Show
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrieveFavoritesUseCase {

    suspend fun get(query: String): List<Show> =
        withContext(Dispatchers.Default) {
            return@withContext arrayListOf()// showRepository.fromNetworkSearch(query)
        }

}