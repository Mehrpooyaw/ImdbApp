package com.example.imdbapp.domain.model

import com.example.imdbapp.network.model.search.Result

data class SearchResults(
    val errorMessage: String?,
    val expression: String?,
    val results: List<Result>?,
    val searchType: String?
)