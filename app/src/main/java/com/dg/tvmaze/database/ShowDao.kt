package com.dg.tvmaze.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dg.tvmaze.entities.Show

@Dao
interface ShowDao {
    @Query("SELECT * FROM shows")
    fun getAll(): List<Show>

    @Query("SELECT * FROM shows WHERE favorite = 1")
    fun getAllFavorites(): List<Show>

    @Insert
    fun insertAll(vararg shows: Show)

    @Delete
    fun delete(show: Show)
}