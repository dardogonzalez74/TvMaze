package com.dg.tvmaze.usecases

import com.dg.tvmaze.entities.Episode
import com.dg.tvmaze.repositories.EpisodeRepository
import kotlinx.coroutines.*

class RetrieveEpisodesUseCase(
    private val episodeRepository: EpisodeRepository
) {

    suspend fun byShowId(showId: Int): List<Episode> =
        withContext(Dispatchers.Default) {
            episodeRepository.fromCacheGetByShowId(showId)?.let { return@withContext it }
            val episodes = episodeRepository.fromNetworkGetByShowId(showId)
            episodeRepository.toCacheSave(showId, episodes)
            return@withContext episodes
        }
}