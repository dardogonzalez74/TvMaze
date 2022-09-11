package com.dg.tvmaze.cache

import com.dg.tvmaze.entities.Episode

class EpisodeCache: IEpisodeCache {

    private val cache = HashMap<Int, List<Episode>>()

    override fun putByShowId(showId: Int, episodes: List<Episode>) {
        cache[showId] = episodes
    }

    override fun getByShowId(showId: Int): List<Episode>? = cache[showId]
}