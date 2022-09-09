package com.dg.tvmaze.network.entities

import com.dg.tvmaze.entities.Episode


data class EpisodeEndpointModel(
    val id: Int,
    val season: Int,
    val number: Int,
    val name: String? = null,
    val summary: String? = null,
    val image: Image? = null
) {
    data class Image(
        val medium: String? = null,
        val original: String? = null
    )
}

fun EpisodeEndpointModel.toAppModel() = Episode(
    id = id,
    season = season,
    number = number,
    name = name,
    thumbnail = image?.medium,
    image = image?.original,
    summary = summary
)
