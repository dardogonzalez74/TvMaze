package com.dg.tvmaze.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dg.tvmaze.entities.Show

@Database(entities = [Show::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    internal abstract fun showDao(): ShowDao
}