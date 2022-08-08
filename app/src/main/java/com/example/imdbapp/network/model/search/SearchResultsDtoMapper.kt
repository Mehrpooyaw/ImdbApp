package com.example.imdbapp.network.model.search


import com.example.imdbapp.domain.model.SearchResults
import com.example.imdbapp.domain.util.DomainMapper
import kotlin.math.exp

class SearchResultsDtoMapper : DomainMapper<SearchResultDto, SearchResults> {
    override fun mapToDomainModel(model: SearchResultDto): SearchResults {
        return SearchResults(
            errorMessage = model.errorMessage,
            expression = model.expression,
            results = model.results,
            searchType = model.searchType
        )
    }



    override fun mapFromDomainModel(domainModel: SearchResults): SearchResultDto {
        TODO("No reason to be implemented")
    }

}