package com.dg.tvmaze.usecases

import com.dg.tvmaze.entities.Episode

interface IRetrieveEpisodesUseCase {
    suspend fun byShowId(showId: Int): List<Episode>
}