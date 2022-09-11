package com.dg.tvmaze.usecases

import com.dg.tvmaze.entities.Episode
import com.dg.tvmaze.network.NetworkException
import com.dg.tvmaze.repositories.IEpisodeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class RetrieveEpisodesUseCaseTest {

    private val cachedShowId = 22
    private val nonCachedShowId = 23
    private val nonExistingShowId = 24
    private val episodeId = 223
    private val episodeNumber = 4
    private val episodeSeason = 3

    private val episodes = List(1) {
        Episode(
            id = episodeId,
            number = episodeNumber,
            season = episodeSeason
        )
    }

    private lateinit var retrieveEpisodesUC: RetrieveEpisodesUseCase

    @Mock
    private lateinit var episodeRepository: IEpisodeRepository

    @Before
    fun setUp() {
        runBlocking {
            Mockito.`when`(episodeRepository.fromCacheGetByShowId(anyInt())).thenReturn(null)
            Mockito.`when`(episodeRepository.fromCacheGetByShowId(cachedShowId)).thenReturn(episodes)

            Mockito.`when`(episodeRepository.fromNetworkGetByShowId(nonCachedShowId)).thenReturn(episodes)
            Mockito.`when`(episodeRepository.fromNetworkGetByShowId(nonExistingShowId)).doAnswer { throw NetworkException(404, "") }

            retrieveEpisodesUC = RetrieveEpisodesUseCase(episodeRepository)
        }
    }

    @Test
    fun `Retrieve episodes from cache`() {
        runBlocking {
            val list = retrieveEpisodesUC.byShowId(cachedShowId)
            assert(list.isNotEmpty()) { "Expecting a non empty list" }
            verify(episodeRepository, times(0)).fromNetworkGetByShowId(anyInt())
        }
    }

    @Test
    fun `Retrieve episodes from network`() {
        runBlocking {
            val list = retrieveEpisodesUC.byShowId(nonCachedShowId)
            assert(list.isNotEmpty()) { "Expecting a non empty list" }
            verify(episodeRepository, times(1)).fromNetworkGetByShowId(nonCachedShowId)
            verify(episodeRepository, times(1)).fromCacheSave(nonCachedShowId, episodes)
        }
    }

    @Test(expected = NetworkException::class)
    fun `Cannot retrieve episodes from network`() {
        runBlocking {
            retrieveEpisodesUC.byShowId(nonExistingShowId)
        }
    }
}