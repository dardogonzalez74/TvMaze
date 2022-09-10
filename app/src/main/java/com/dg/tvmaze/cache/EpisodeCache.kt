package com.dg.tvmaze.cache

import com.dg.tvmaze.entities.Episode

class EpisodeCache {

    private val cache = HashMap<Int, List<Episode>>()

    fun putByShowId(showId: Int, episodes: List<Episode>) {
        cache[showId] = episodes
    }

    fun getByShowId(showId: Int): List<Episode>? = cache[showId]
}