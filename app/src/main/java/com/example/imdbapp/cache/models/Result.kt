package com.example.imdbapp.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.imdbapp.domain.model.advanced_search.TopMovieType
import com.example.imdbapp.presentation.ui.categories.components.Genres

@Entity(tableName = "search_results")
data class ResultEntity(
    var contentRating: String?,
    var description: String?,
    var genres: String?,
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var imDbRating: String?,
    var imDbRatingVotes: String?,
    var image: String?,
    var metacriticRating: String?,
    var plot: String?,
    var runtimeStr: String?,
    var stars: String?,
    var title: String?,
    var genreType : Genres? = null,
    var topMovieType : TopMovieType? = null
)