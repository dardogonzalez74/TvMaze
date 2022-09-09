package com.dg.tvmaze.di

import com.dg.tvmaze.ui.list.ListViewModel
import com.dg.tvmaze.ui.series.ShowDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { ListViewModel(retrieveShowsPagedUC = get()) }
    viewModel { ShowDetailsViewModel(retrieveEpisodesUseCase = get()) }
}