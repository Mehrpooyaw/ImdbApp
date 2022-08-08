package com.example.imdbapp.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val title : String?,
    val image : String?,
    val imdbRating : String?,
    val plot :String?
)