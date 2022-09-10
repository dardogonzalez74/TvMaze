package com.dg.tvmaze.usecases

import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.repositories.ShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrieveFavoritesUseCase(
    private val showRepository: ShowRepository
) {

    suspend fun getAll(): List<Show> =
        withContext(Dispatchers.Default) {
            // We should retrieve the shows with favorite=true
            // but because the only use that we give to it
            // is for storing the favorites shows we simplify
            // the logic by retrieving all to the table
            return@withContext showRepository.fromDbGetAll()
        }

}