package com.dg.tvmaze.usecases

import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.repositories.ShowRepository
import kotlinx.coroutines.*

class RetrieveShowUseCase(
    private val showRepository: ShowRepository
) {

    suspend fun byId(id: Int): Show =
        withContext(Dispatchers.Default) {
            delay(1_000)
            return@withContext showRepository.fromNetworkById(id)
        }

}