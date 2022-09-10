package com.dg.tvmaze.database

import androidx.room.*
import com.dg.tvmaze.entities.Show

@Dao
interface ShowDao {
    @Query("SELECT * FROM shows")
    suspend fun getAll(): List<Show>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWithReplace(show: Show) : Long

    @Delete
    suspend fun delete(show: Show)
}