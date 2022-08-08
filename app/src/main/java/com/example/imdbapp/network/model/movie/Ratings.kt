package com.example.imdbapp.network.model.movie

data class Ratings(
    val errorMessage: String?,
    val filmAffinity: String?,
    val fullTitle: String?,
    val imDb: String?,
    val imDbId: String?,
    val metacritic: String?,
    val rottenTomatoes: String?,
    val tV_com: String?,
    val theMovieDb: String?,
    val title: String?,
    val type: String?,
    val year: String?
)