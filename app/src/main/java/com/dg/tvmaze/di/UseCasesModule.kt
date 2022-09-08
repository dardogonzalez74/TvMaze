package com.dg.tvmaze.di

import com.dg.tvmaze.usecases.RetrieveShowUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { RetrieveShowUseCase(showRepository = get()) }
}