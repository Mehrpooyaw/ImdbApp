package com.example.imdbapp.presentation.ui.explore

import com.example.imdbapp.domain.model.advanced_search.TopMovieType

sealed class ExploreEvent {
    data class GetTopMovies(
        val type : TopMovieType = TopMovieType.Top250Movies
    ) : ExploreEvent()

    data class GetTopAwarded(val type : TopMovieType = TopMovieType.Oscar) : ExploreEvent()
}
