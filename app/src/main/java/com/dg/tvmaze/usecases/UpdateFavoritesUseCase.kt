package com.dg.tvmaze.usecases

import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.repositories.ShowRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateFavoritesUseCase(
    private val showRepository: ShowRepository
) {

    fun add(show: Show) {
        CoroutineScope(Dispatchers.Default).launch {
            // We should set favorite=true in the shows table
            // but because the only use that we give to it
            // is for storing the favorites shows we simplify
            // the logic by adding them to the table
            showRepository.fromDbInsert(show.copy(favorite = true))
        }
    }

    fun remove(show: Show) {
        CoroutineScope(Dispatchers.Default).launch {
            // We should set favorite=false in the shows table
            // but because the only use that we give to it
            // is for storing the favorites shows we simplify
            // the logic by removing them from the table
            showRepository.fromDbInsert(show.copy(favorite = true))
        }
    }
}