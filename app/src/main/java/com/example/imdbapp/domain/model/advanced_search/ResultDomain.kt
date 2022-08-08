package com.example.imdbapp.domain.model.advanced_search

import com.example.imdbapp.network.model.advanced_search.Genre
import com.example.imdbapp.network.model.advanced_search.Star
import com.example.imdbapp.presentation.ui.categories.components.Genres

data class ResultDomain(
    var contentRating: String?,
    var description: String?,
    var genreList: List<Genre>?,
    var genres: String?,
    var id: String?,
    var imDbRating: String?,
    var imDbRatingVotes: String?,
    var image: String?,
    var metacriticRating: String?,
    var plot: String?,
    var runtimeStr: String?,
    var starList: List<Star>?,
    var stars: String?,
    var title: String?,
    var genreType : Genres? = null,
    var topMovieType : TopMovieType? = null
)




enum class TopMovieType(val str : String){
    Top250Movies(str = "top_250"),
    BestPic(str = "oscar_best_picture_winners"),
    Oscar(str = "oscar_winners"),
    Emmy(str = "emmy_winners"),
    GoldenGlobe(str = "golden_globe_winners"),
    BestDirector("oscar_best_director_winners"),
    None("None"),

}