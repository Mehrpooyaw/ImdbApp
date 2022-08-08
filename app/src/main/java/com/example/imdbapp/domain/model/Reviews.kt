package com.example.imdbapp.domain.model

import com.example.imdbapp.network.model.reviews.Review

data class Reviews(
    val errorMessage: String?,
    val fullTitle: String?,
    val imDbId: String?,
    val items: List<Review>?,
    val title: String?,
    val type: String?,
    val year: String?
)