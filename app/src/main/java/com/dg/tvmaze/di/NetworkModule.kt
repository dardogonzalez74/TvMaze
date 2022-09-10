package com.dg.tvmaze.di

import com.dg.tvmaze.network.EpisodeEndpoint
import com.dg.tvmaze.network.RetrofitProvider
import com.dg.tvmaze.network.SearchEndpoint
import com.dg.tvmaze.network.ShowEndpoint
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single { RetrofitProvider().provide() }

    single { get<Retrofit>().create(ShowEndpoint::class.java) }
    single { get<Retrofit>().create(EpisodeEndpoint::class.java) }
    single { get<Retrofit>().create(SearchEndpoint::class.java) }
}