package com.example.imdbapp.network.model.advanced_search

import com.example.imdbapp.presentation.ui.advanced_search.AdvancedSearchType

data class AdvancedSearchOptions(
    val query : String? = null,
    val type : AdvancedSearchType = AdvancedSearchType.SearchAll,
    val genres : List<String> = listOf(),
    val imdbRateFrom : String= "",
    val imdbRateTo : String= "",
    val dateFrom : String= "",
    val dateTo : String = "",
)
