package com.example.imdbapp.network.model.season_episodes

data class SeasonEpisode(
    val episodes: List<Episode>?,
    val errorMessage: String?,
    val fullTitle: String?,
    val imDbId: String?,
    val title: String?,
    val type: String?,
    val year: String?
)