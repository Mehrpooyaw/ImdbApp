package com.example.imdbapp.network.model.season_episodes

data class Episode(
    val episodeNumber: String?,
    val id: String?,
    val imDbRating: String?,
    val imDbRatingCount: String?,
    val image: String?,
    val plot: String?,
    val released: String?,
    val seasonNumber: String?,
    val title: String?,
    val year: String?
)