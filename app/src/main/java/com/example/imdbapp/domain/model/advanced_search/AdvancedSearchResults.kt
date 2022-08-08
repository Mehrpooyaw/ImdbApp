package com.example.imdbapp.domain.model.advanced_search

import com.example.imdbapp.network.model.advanced_search.Result

data class AdvancedSearchResults(
    var errorMessage: String?,
    var queryString: String?,
    var results: List<Result>?
)
