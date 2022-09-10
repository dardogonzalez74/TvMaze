package com.dg.tvmaze.di

import com.dg.tvmaze.ui.main.MainViewModel
import com.dg.tvmaze.ui.series.ShowDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { MainViewModel(retrieveShowsPagedUC = get()) }
    viewModel { ShowDetailsViewModel(retrieveEpisodesUseCase = get()) }
}