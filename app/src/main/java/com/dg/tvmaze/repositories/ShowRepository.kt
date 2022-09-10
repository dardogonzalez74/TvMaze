package com.dg.tvmaze.repositories

import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.extensions.parse
import com.dg.tvmaze.network.ShowEndpoint
import com.dg.tvmaze.network.entities.toAppModel


class ShowRepository(
    private val showEndpoint: ShowEndpoint
) {

    suspend fun fromNetworkById(id: Int): Show =
        showEndpoint.getById(id).parse().toAppModel()

    suspend fun fromNetworkByPage(page: Int): List<Show> =
        showEndpoint.getByPage(page).parse().map { it.toAppModel() }

}

