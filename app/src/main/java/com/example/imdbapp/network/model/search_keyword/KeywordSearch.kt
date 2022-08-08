package com.example.imdbapp.network.model.search_keyword

data class KeywordSearch(
    val errorMessage: String?,
    val items: List<Item>?,
    val keyword: String?
)