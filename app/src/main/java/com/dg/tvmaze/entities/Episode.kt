package com.dg.tvmaze.entities

data class Episode(
    val id: Int,
    val season: Int,
    val number: Int,
    val name: String? = null,
    val summary: String? = null,
    val thumbnail: String? = null,
    val image: String? = null
)