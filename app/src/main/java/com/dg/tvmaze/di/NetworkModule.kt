package com.dg.tvmaze.di

import com.dg.tvmaze.network.RetrofitProvider
import com.dg.tvmaze.network.ShowsEndpoint
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single { RetrofitProvider().provide() }

    single { get<Retrofit>().create(ShowsEndpoint::class.java) }

}