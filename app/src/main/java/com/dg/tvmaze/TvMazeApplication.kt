package com.dg.tvmaze

import android.support.multidex.MultiDexApplication
import com.dg.tvmaze.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TvMazeApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TvMazeApplication)
            modules(
                databaseModule,
                cachesModule,
                networkModule,
                repositoriesModule,
                useCasesModule,
                viewModelsModule
            )
        }
    }
}