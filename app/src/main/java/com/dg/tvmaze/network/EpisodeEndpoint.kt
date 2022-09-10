package com.dg.tvmaze.network

import com.dg.tvmaze.network.entities.EpisodeEndpointModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeEndpoint {

    @GET("shows/{id}/episodes")
    suspend fun getByShowId(@Path("id") showId: Int): Response<List<EpisodeEndpointModel>>

}