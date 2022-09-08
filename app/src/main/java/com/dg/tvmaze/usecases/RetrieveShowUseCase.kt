package com.dg.tvmaze.usecases

import com.dg.tvmaze.entities.Show
import kotlinx.coroutines.*

class RetrieveShowUseCase {

    suspend fun byId(id: Int): Show =
        withContext(Dispatchers.Default) {
            delay(1_000)
            return@withContext Show(id, "Desc for $id")
        }

}