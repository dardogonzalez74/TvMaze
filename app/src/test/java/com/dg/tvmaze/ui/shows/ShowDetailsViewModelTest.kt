package com.dg.tvmaze.ui.shows

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dg.tvmaze.entities.Episode
import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.getOrAwaitValue
import com.dg.tvmaze.network.NetworkException
import com.dg.tvmaze.usecases.IRetrieveEpisodesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@ExperimentalCoroutinesApi
class ShowDetailsViewModelTest {

    private val existingShowId = 22
    private val nonExistingShowId = 23
    private val showName = "Name for 22"

    private val episodeId = 223
    private val episodeNumber = 4
    private val episodeSeason = 3

    private val networkException = NetworkException(404, "")
    private val existingShow = Show(id = existingShowId, name = showName)
    private val nonExistingShow = Show(id = nonExistingShowId, name = showName)
    private val episodes = List(1) { Episode(id = episodeId, number = episodeNumber, season = episodeSeason) }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var showDetailsViewModel: ShowDetailsViewModel

    @MockK
    private lateinit var retrieveEpisodesUseCase: IRetrieveEpisodesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        coEvery { retrieveEpisodesUseCase.byShowId(existingShowId) } returns episodes
        coEvery { retrieveEpisodesUseCase.byShowId(nonExistingShowId) } throws networkException
        showDetailsViewModel = ShowDetailsViewModel(retrieveEpisodesUseCase)
    }

    @Test
    fun `Check livedata when a valid showId`() {
        mainCoroutineRule.runBlockingTest {
            showDetailsViewModel.initWith(existingShow)
            Assert.assertEquals(showDetailsViewModel.episodes.getOrAwaitValue(), Result.success(episodes))
        }
    }

    @Test
    fun `Check livedata when an invalid showId`() {
        mainCoroutineRule.runBlockingTest {
            showDetailsViewModel.initWith(nonExistingShow)
            Assert.assertEquals(
                showDetailsViewModel.episodes.getOrAwaitValue(),
                Result.failure<List<Episode>>(networkException)
            )
        }
    }
}











