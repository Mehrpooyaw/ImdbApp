package com.example.imdbapp.network.model.movie


data class Images(
    val errorMessage: String?,
    val fullTitle: String?,
    val imDbId: String?,
    val items: List<ItemXXX>?,
    val title: String?,
    val type: String?,
    val year: String?
)