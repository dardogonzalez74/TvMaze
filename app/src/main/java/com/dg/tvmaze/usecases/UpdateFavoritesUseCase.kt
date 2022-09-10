package com.dg.tvmaze.usecases

import com.dg.tvmaze.entities.Show
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateFavoritesUseCase {

    fun add(show: Show) {
        CoroutineScope(Dispatchers.Default).launch {
        }
    }

    fun remove(show: Show) {
        CoroutineScope(Dispatchers.Default).launch {
        }
    }
}