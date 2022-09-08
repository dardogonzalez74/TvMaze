package com.dg.tvmaze.repositories

import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.extensions.endpointModel
import com.dg.tvmaze.network.ShowsEndpoint
import com.dg.tvmaze.network.entities.toAppModel


class ShowRepository(
    private val showsEndpoint: ShowsEndpoint
) {

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun fromNetworkById(id: Int): Show =
        showsEndpoint.getById(id).endpointModel().toAppModel()

}

