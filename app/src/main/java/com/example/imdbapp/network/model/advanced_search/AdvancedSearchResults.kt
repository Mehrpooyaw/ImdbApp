package com.example.imdbapp.network.model.advanced_search

data class AdvancedSearchResults(
    var errorMessage: String?,
    var queryString: String?,
    var results: List<Result>?
)