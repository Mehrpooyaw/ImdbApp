package com.example.imdbapp.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "box_office_movie_all_time")
data class BoxOfficeMovieAllTimeEntity(
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