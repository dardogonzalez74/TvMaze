package com.dg.tvmaze.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Episode(
    val id: Int,
    val season: Int,
    val number: Int,
    val name: String? = null,
    val summary: String? = null,
    val thumbnail: String? = null,
    val image: String? = null
): Parcelable