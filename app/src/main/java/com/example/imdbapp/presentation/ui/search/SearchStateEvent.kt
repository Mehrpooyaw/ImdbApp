package com.example.imdbapp.presentation.ui.search

sealed class SearchEvent {
    object NewSearchEvent : SearchEvent()
}