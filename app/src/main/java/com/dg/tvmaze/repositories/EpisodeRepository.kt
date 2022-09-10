package com.dg.tvmaze.repositories

import com.dg.tvmaze.cache.EpisodeCache
import com.dg.tvmaze.entities.Episode
import com.dg.tvmaze.extensions.parse
import com.dg.tvmaze.network.EpisodeEndpoint
import com.dg.tvmaze.network.entities.toAppModel

class EpisodeRepository(
    private val episodeEndpoint: EpisodeEndpoint,
    private val episodeCache: EpisodeCache
) {

    suspend fun fromNetworkByShowId(showId: Int): List<Episode> =
        episodeEndpoint.getByShowId(showId).parse().map { it.toAppModel() }

    fun fromCacheByShowId(showId: Int): List<Episode> ?=
        episodeCache.getByShowId(showId)

    fun toCache(showId: Int, episode: List<Episode>) {
        episodeCache.putByShowId(showId, episode)
    }

}