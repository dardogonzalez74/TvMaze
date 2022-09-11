package com.dg.tvmaze.repositories

import com.dg.tvmaze.entities.Episode

interface IEpisodeRepository {
    suspend fun fromNetworkGetByShowId(showId: Int): List<Episode>
    fun fromCacheGetByShowId(showId: Int): List<Episode>?
    fun fromCacheSave(showId: Int, episode: List<Episode>)
}