package com.dg.tvmaze.network

import com.dg.tvmaze.network.entities.SearchShowEndpointModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchEndpoint {

    @GET("/search/shows")
    suspend fun searchShows(@Query("q") query: String): Response<List<SearchShowEndpointModel>>

}