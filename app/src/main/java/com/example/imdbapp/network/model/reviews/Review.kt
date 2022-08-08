package com.example.imdbapp.network.model.reviews

data class Review(
    val content: String?,
    val date: String?,
    val helpful: String?,
    val rate: String?,
    val reviewLink: String?,
    val title: String?,
    val userUrl: String?,
    val username: String?,
    val warningSpoilers: Boolean?
)