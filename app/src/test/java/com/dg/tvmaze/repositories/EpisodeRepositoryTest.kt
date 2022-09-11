package com.dg.tvmaze.repositories

import com.dg.tvmaze.cache.IEpisodeCache
import com.dg.tvmaze.entities.Episode
import com.dg.tvmaze.network.EpisodeEndpoint
import com.dg.tvmaze.network.NetworkException
import com.dg.tvmaze.network.entities.EpisodeEndpointModel
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSource
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class EpisodeRepositoryTest {

    private val showId = 22
    private val episodeId = 223
    private val episodeNumber = 4
    private val episodeSeason = 3
    private val episodeName = "Ep. name"
    private val episodeSummary = "Ep. summary"
    private val imageMedium = "Image medium"
    private val imageOriginal = "Image original"
    private val episodeImage = EpisodeEndpointModel.Image(medium = imageMedium, original = imageOriginal)

    private val episodesEndpointModel = List(1) {
        EpisodeEndpointModel(
            id = episodeId,
            number = episodeNumber,
            season = episodeSeason,
            name = episodeName,
            summary = episodeSummary,
            image = episodeImage
        )
    }

    private val episodes = List(1) {
        Episode(
            id = episodeId,
            number = episodeNumber,
            season = episodeSeason,
            name = episodeName,
            summary = episodeSummary,
            image = imageOriginal,
            thumbnail = imageMedium
        )
    }

    private val responseError = object : ResponseBody() {
        override fun contentLength() = 0L
        override fun contentType(): MediaType? = null
        override fun source(): BufferedSource = Buffer()
    }

    private lateinit var episodeRepository: EpisodeRepository

    @Mock
    private lateinit var episodeEndpoint: EpisodeEndpoint

    @Mock
    private lateinit var episodeCache: IEpisodeCache

    @Before
    fun setUp() {
        runBlocking {
            Mockito.`when`(episodeEndpoint.getByShowId(anyInt())).thenReturn(Response.error(404, responseError))
            Mockito.`when`(episodeEndpoint.getByShowId(showId)).thenReturn(Response.success(episodesEndpointModel))

            Mockito.`when`(episodeCache.getByShowId(anyInt())).thenReturn(null)
            Mockito.`when`(episodeCache.getByShowId(showId)).thenReturn(episodes)

            episodeRepository = EpisodeRepository(episodeEndpoint, episodeCache)
        }
    }

    @Test
    fun `Retrieve episodes from network`() {
        runBlocking {
            val response = episodeRepository.fromNetworkGetByShowId(showId)
            assert(response.size == episodesEndpointModel.size) { "List of different sizes" }
            assert(response[0].id == episodeId) { "Id field not mapped" }
            assert(response[0].number == episodeNumber) { "Number field not mapped" }
            assert(response[0].season == episodeSeason) { "Season field not mapped" }
            assert(response[0].name == episodeName) { "Name field not mapped" }
            assert(response[0].summary == episodeSummary) { "Summary field not mapped" }
            assert(response[0].image == imageOriginal) { "Image field not mapped" }
            assert(response[0].thumbnail == imageMedium) { "Thumbnail field not mapped" }
        }
    }

    @Test(expected = NetworkException::class)
    fun `Retrieve non existing episodes from network`() {
        runBlocking {
            episodeRepository.fromNetworkGetByShowId(showId + 1)
        }
    }

    @Test
    fun `Retrieve episodes from cache`() {
        runBlocking {
            val list = episodeRepository.fromCacheGetByShowId(showId)
            assert(list != null) { "Episodes not received" }
            assert(list!!.size == episodesEndpointModel.size) { "List of different sizes" }
            assert(list[0].id == episodeId) { "Id field mismatch" }
            assert(list[0].number == episodeNumber) { "Number field mismatch" }
            assert(list[0].season == episodeSeason) { "Season field mismatch" }
            assert(list[0].name == episodeName) { "Name field mismatch" }
            assert(list[0].summary == episodeSummary) { "Summary field mismatch" }
            assert(list[0].image == imageOriginal) { "Image field mismatch" }
            assert(list[0].thumbnail == imageMedium) { "Thumbnail field mismatch" }
        }
    }

    @Test
    fun `Retrieve non existing episodes from cache`() {
        runBlocking {
            val response = episodeRepository.fromCacheGetByShowId(showId + 1)
            assert(response == null) { "Episodes received" }
        }
    }

    @Test
    fun `Save episodes in cache`() {
        runBlocking {
            episodeRepository.fromCacheSave(showId, episodes)
            verify(episodeCache, times(1)).putByShowId(showId, episodes)
        }
    }

}









