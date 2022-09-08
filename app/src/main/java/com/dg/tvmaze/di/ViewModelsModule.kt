package com.dg.tvmaze.di

import com.dg.tvmaze.ui.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel { ListViewModel() }
}