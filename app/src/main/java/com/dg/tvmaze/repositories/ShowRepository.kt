package com.dg.tvmaze.repositories

import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.extensions.parse
import com.dg.tvmaze.network.ShowsEndpoint
import com.dg.tvmaze.network.entities.toAppModel


class ShowRepository(
    private val showsEndpoint: ShowsEndpoint
) {

    suspend fun fromNetworkById(id: Int): Show =
        showsEndpoint.getById(id).parse().toAppModel()

    suspend fun fromNetworkByPage(page: Int): List<Show> =
        showsEndpoint.getByPage(page).parse().map { it.toAppModel() }

}

