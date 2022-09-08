package com.dg.tvmaze.network

import com.dg.tvmaze.network.entities.ShowsEndpointModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ShowsEndpoint {

    @GET("shows1/{id}")
    suspend fun getById(@Path("id") id: Int): Response<ShowsEndpointModel>

}