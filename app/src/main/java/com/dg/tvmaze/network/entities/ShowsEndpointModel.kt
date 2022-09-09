package com.dg.tvmaze.network.entities

import com.dg.tvmaze.entities.Show

data class ShowsEndpointModel(
    val id: Int,
    val name: String,
    val summary: String? = null,
    val image: Image? = null,
    val network: Network? = null
) {

    data class Image(
        val medium: String? = null,
        val original: String? = null
    )

    data class Network(
        val name: String? = null,
    )
}


fun ShowsEndpointModel.toAppModel() = Show(
    id = id,
    name = name,
    thumbnail = image?.medium,
    image = image?.original,
    summary = summary,
    propertyOf = network?.name
)
