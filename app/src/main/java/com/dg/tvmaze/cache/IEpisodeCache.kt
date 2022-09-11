package com.dg.tvmaze.cache

import com.dg.tvmaze.entities.Episode

interface IEpisodeCache {
    fun putByShowId(showId: Int, episodes: List<Episode>)
    fun getByShowId(showId: Int): List<Episode>?
}