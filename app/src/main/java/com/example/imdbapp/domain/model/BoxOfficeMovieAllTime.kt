package com.example.imdbapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class BoxOfficeMovieAllTime(
    val domestic: String?,
    val domesticLifetimeGross: String?,
    val foreign: String?,
    val foreignLifetimeGross: String?,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val rank: String?,
    val title: String?,
    val worldwideLifetimeGross: String?,
    val year: String?
)