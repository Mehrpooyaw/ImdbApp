package com.example.imdbapp.network.model.movie




data class FullCast(

    val actors: List<ActorX>?,

    val directors: Directors?,
    val errorMessage: String?,
    val fullTitle: String?,
    val imDbId: String?,

    val others: List<Other>?,
    val title: String?,
    val type: String?,

    val writers: Writers?,
    val year: String?
)