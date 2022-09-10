package com.dg.tvmaze.usecases

import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.repositories.ShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrieveShowsBySearchUseCase(
    private val showRepository: ShowRepository
) {
    suspend fun search(query: String): List<Show> =
        withContext(Dispatchers.Default) {
            return@withContext showRepository.fromNetworkSearch(query)
        }
}