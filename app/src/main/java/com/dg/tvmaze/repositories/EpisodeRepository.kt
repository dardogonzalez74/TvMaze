package com.dg.tvmaze.repositories

import com.dg.tvmaze.cache.IEpisodeCache
import com.dg.tvmaze.entities.Episode
import com.dg.tvmaze.extensions.parse
import com.dg.tvmaze.network.EpisodeEndpoint
import com.dg.tvmaze.network.entities.toAppModel

class EpisodeRepository(
    private val episodeEndpoint: EpisodeEndpoint,
    private val episodeCache: IEpisodeCache
):IEpisodeRepository {

    override suspend fun fromNetworkGetByShowId(showId: Int): List<Episode> =
        episodeEndpoint.getByShowId(showId).parse().map { it.toAppModel() }

    override fun fromCacheGetByShowId(showId: Int): List<Episode>? =
        episodeCache.getByShowId(showId)

    override fun fromCacheSave(showId: Int, episode: List<Episode>) {
        episodeCache.putByShowId(showId, episode)
    }

}