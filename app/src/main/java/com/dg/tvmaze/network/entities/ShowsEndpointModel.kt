package com.dg.tvmaze.network.entities

import com.dg.tvmaze.entities.Show

data class ShowsEndpointModel(
    val id: Int,
    val name: String
)

fun ShowsEndpointModel.toAppModel() = Show(
    id = id,
    name = name
)
