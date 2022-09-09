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
): Parcelable