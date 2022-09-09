package com.dg.tvmaze.network.entities

import com.dg.tvmaze.entities.Show

data class ShowsEndpointModel(
    val id: Int,
    val name: String,
    val summary: String? = null,
    val image: Image? = null,
    val network: Network? = null,
    val language: String? = null,
    val runtime: Int? = null,
    val premiered: String? = null,
    val ended: String? = null,
    val genres: List<String>? = null,
    val rating: Rating? = null,
    val schedule: Schedule? = null

) {

    data class Image(
        val medium: String? = null,
        val original: String? = null
    )

    data class Rating(
        val average: Double? = null,
        val averageRuntime: Double? = null,
    )

    data class Schedule(
        val time: String? = null,
        val days: List<String>? = null,
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
    propertyOf = network?.name,
    language = language,
    runtime = runtime,
    premiered = premiered,
    ended = ended,
    genres = genres,
    rating = rating?.average?: rating?.averageRuntime,
    time = schedule?.time?.let { it.ifEmpty { null } },
    days = schedule?.days?.let { it.ifEmpty { null } }
)
