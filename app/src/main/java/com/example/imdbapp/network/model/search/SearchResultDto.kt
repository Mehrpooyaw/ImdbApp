package com.example.imdbapp.network.model.search

data class SearchResultDto(
    val errorMessage: String?,
    val expression: String?,
    val results: List<Result>?,
    val searchType: String?
)