package com.dg.tvmaze.network

import com.dg.tvmaze.network.entities.ShowEndpointModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowEndpoint {

    @GET("shows/{id}")
    suspend fun getById(@Path("id") id: Int): Response<ShowEndpointModel>

    @GET("shows")
    suspend fun getByPage(@Query("page") page: Int): Response<List<ShowEndpointModel>>

}