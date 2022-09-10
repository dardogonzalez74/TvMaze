package com.dg.tvmaze.usecases

import com.dg.tvmaze.entities.Episode
import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.repositories.EpisodeRepository
import com.dg.tvmaze.repositories.ShowRepository
import kotlinx.coroutines.*

class RetrieveEpisodesUseCase(
    private val episodeRepository: EpisodeRepository
) {

    suspend fun byShowId(showId: Int): List<Episode> =
        withContext(Dispatchers.Default) {
            episodeRepository.fromCacheByShowId(showId)?.let { return@withContext it }
            val episodes = episodeRepository.fromNetworkByShowId(showId)
            episodeRepository.toCache(showId, episodes)
            return@withContext episodes
        }
}