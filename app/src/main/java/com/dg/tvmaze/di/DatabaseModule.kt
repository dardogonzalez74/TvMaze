package com.dg.tvmaze.di

import androidx.room.Room
import com.dg.tvmaze.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "tv_maze_db").build() }

    single { get<AppDatabase>().showDao() }
}