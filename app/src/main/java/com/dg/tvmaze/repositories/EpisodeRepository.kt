package com.dg.tvmaze.repositories

import com.dg.tvmaze.entities.Episode
import com.dg.tvmaze.extensions.parse
import com.dg.tvmaze.network.EpisodesEndpoint
import com.dg.tvmaze.network.entities.toAppModel

class EpisodeRepository(
    private val episodesEndpoint: EpisodesEndpoint
) {

    suspend fun fromNetworkByShowId(id: Int): List<Episode> =
        episodesEndpoint.getByShowId(id).parse().map { it.toAppModel() }

}