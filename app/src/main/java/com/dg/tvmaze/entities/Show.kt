package com.dg.tvmaze.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "shows")
@Parcelize
data class Show(
    @PrimaryKey val id: Int,
    val name: String,
    val thumbnail: String? = null,
    val image: String? = null,
    val summary: String? = null,
    val propertyOf: String? = null,
    val language: String? = null,
    val runtime: Int? = null,
    val premiered: String? = null,
    val ended: String? = null,
    val genres: String? = null,
    val rating: Double? = null,
    val time: String? = null,
    val days: String? = null,
    var favorite: Boolean = false
): Parcelable