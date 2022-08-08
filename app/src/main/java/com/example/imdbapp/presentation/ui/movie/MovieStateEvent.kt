package com.example.imdbapp.presentation.ui.movie

sealed class MovieEvent {

    data class GetMovieEvent(
        val id : String,
    ) : MovieEvent()

    data class GetReviewsEvent(
        val id : String,
    ) : MovieEvent()
}