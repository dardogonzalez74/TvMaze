package com.dg.tvmaze.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Show(
    val id: Int,
    val name: String,
    val thumbnail: String? = null,
    val image: String? = null,
    val summary: String? = null,
    val propertyOf: String? = null,
    val language: String? = null,
    val runtime: Int? = null,
    val premiered: String? = null,
    val ended: String? = null,
    val genres: List<String>? = null,
    val rating: Double? = null,
    val time: String? = null,
    val days: List<String>? = null
): Parcelable