package com.dg.tvmaze.usecases

import com.dg.tvmaze.entities.Episode
import com.dg.tvmaze.repositories.IEpisodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrieveEpisodesUseCase(
    private val episodeRepository: IEpisodeRepository
) : IRetrieveEpisodesUseCase {

    override suspend fun byShowId(showId: Int): List<Episode> =
        withContext(Dispatchers.Default) {
            episodeRepository.fromCacheGetByShowId(showId)?.let { return@withContext it }
            val episodes = episodeRepository.fromNetworkGetByShowId(showId)
            episodeRepository.fromCacheSave(showId, episodes)
            return@withContext episodes
        }
}