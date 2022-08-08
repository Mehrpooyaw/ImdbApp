package com.example.imdbapp.network.model.listItemMovies

import kotlin.collections.List

data class ListOfItems(
    val errorMessage: String?,
    val items: List<Item>?
)


// For example : Top 250 movies, most popular movies and ... .
