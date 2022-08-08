package com.example.imdbapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.imdbapp.domain.model.advanced_search.TopMovieType
import java.time.Year

@Entity(tableName = "top_movie")
data class TopMovie(
    var type : TopMovieType = TopMovieType.None,
    @PrimaryKey(autoGenerate = false)
    var id : String,
    var rank : String?,
    var rankUpDown : String?,
    var title : String?,
    var fullTitle : String?,
    var year : String?,
    var image : String?,
    var crew : String?,
    var imdbRating : String?,
    var imdbContentRating : String?
)

