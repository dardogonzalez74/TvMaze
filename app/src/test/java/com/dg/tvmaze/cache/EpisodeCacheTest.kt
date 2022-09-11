package com.dg.tvmaze.cache

import com.dg.tvmaze.entities.Episode
import com.dg.tvmaze.entities.Show
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EpisodeCacheTest {

    private lateinit var episodeCache: EpisodeCache
    private val showId = 22
    private val episodes = List(5) {
        Episode(id = it, name = "Name $it", number = it, season = it)
    }

    @Before
    fun setUp() {
        episodeCache = EpisodeCache()
        episodeCache.putByShowId(showId, episodes)
    }

    @Test
    fun `Look for an existing item`() {
        assert(episodeCache.getByShowId(showId)?.size == episodes.size)
    }

    @Test
    fun `Look for a non existing item`() {
        assert(episodeCache.getByShowId(showId + 1) == null)
    }

}