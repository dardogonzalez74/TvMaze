package com.dg.tvmaze.entities

data class Show(
    val id: Int,
    val name: String,
    val thumbnail: String? = null,
    val image: String? = null
)