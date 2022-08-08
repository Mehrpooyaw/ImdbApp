package com.example.imdbapp.network.model.advanced_search

data class Result(
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
    var title: String?
)