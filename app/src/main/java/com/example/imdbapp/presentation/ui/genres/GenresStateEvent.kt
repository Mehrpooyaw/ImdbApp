package com.example.imdbapp.presentation.ui.genres

import com.example.imdbapp.presentation.ui.categories.components.Genres

sealed class GenresEvent {
    data class GetByGenre(val genre : Genres) : GenresEvent()
}