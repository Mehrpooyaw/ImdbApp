package com.example.imdbapp.network.model.movie

import androidx.room.Embedded

data class Posters(

    @Embedded(prefix = "backdrop_")
    val backdrops: List<Backdrop>?,
    val errorMessage: String?,
    val fullTitle: String?,
    val imDbId: String?,

    @Embedded(prefix = "poster_")
    val posters: List<Poster>?,
    val title: String?,
    val type: String?,
    val year: String?
)