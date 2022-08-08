package com.example.imdbapp.network.model.reviews

data class ReviewsModel(
    val errorMessage: String?,
    val fullTitle: String?,
    val imDbId: String?,
    val items: List<Review>?,
    val title: String?,
    val type: String?,
    val year: String?
)