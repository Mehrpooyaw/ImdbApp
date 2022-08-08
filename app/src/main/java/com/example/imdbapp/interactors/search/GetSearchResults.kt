package com.example.imdbapp.interactors.search

import com.example.imdbapp.domain.data.DataState
import com.example.imdbapp.domain.model.SearchResults
import com.example.imdbapp.network.NetworkService
import com.example.imdbapp.network.model.search.SearchResultsDtoMapper
import com.example.imdbapp.presentation.ui.search.SearchType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import kotlin.Exception



class GetSearchResults(
    private val networkService: NetworkService,
    private val dtoMapper : SearchResultsDtoMapper,

) {
    fun execute(
        apiKey: String,
        searchType : SearchType,
        query: String
    ): Flow<DataState<SearchResults>> = flow {
        try {
            emit(DataState.loading())

            val networkResults = getSearchResultsFromNetwork(apiKey, query,searchType)

            emit(DataState.success(data = networkResults))

        } catch (e: Exception) {
            emit(DataState.error(message = e.message?:" Unknown error"))
        }

    }
    private suspend fun getSearchResultsFromNetwork(apiKey: String, query: String, searchType: SearchType): SearchResults {
        return dtoMapper.mapToDomainModel(networkService.search(apiKey = apiKey, expression = query, searchType = searchType))

    }
}


