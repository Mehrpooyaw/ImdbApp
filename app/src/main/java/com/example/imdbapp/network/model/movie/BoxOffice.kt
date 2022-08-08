package com.example.imdbapp.network.model.movie

data class BoxOffice(
    val budget: String?,
    val cumulativeWorldwideGross: String?,
    val grossUSA: String?,
    val openingWeekendUSA: String?
)