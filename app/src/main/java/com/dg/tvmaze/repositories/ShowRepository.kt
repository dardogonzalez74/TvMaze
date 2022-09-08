package com.dg.tvmaze.repositories

import com.dg.tvmaze.entities.Show


class ShowRepository {

    suspend fun fromNetworkById(id: Int): Show =
        Show(id, "Desc for $id")

}