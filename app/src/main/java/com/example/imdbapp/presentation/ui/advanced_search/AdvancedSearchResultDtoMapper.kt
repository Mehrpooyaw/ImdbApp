package com.example.imdbapp.presentation.ui.advanced_search

import com.example.imdbapp.cache.models.ResultEntity
import com.example.imdbapp.domain.model.advanced_search.ResultDomain
import com.example.imdbapp.domain.util.DomainMapper
import com.example.imdbapp.network.model.advanced_search.Result

class AdvancedSearchResultDtoMapper : DomainMapper<Result, ResultDomain> {
    override fun mapToDomainModel(model: Result): ResultDomain {
        return ResultDomain(
            contentRating = model.contentRating,
            description= model.description,
            genres = model.genres,
            id = model.id?:"11111",
            image = model.image,
            imDbRating = model.imDbRating,
            imDbRatingVotes = model.imDbRatingVotes,
            runtimeStr = model.runtimeStr ,
            metacriticRating = model.metacriticRating,
            stars = model.stars,
            plot = model.plot,
            title = model.title,
            genreList = null,
            starList = null,
        )
    }

    fun toDomainList(list : List<Result>?) : List<ResultDomain>{
        val returnedList : ArrayList<ResultDomain> = ArrayList()
        list?.forEach {
            returnedList.add(mapToDomainModel(it))
        }
        return returnedList.toList()
    }





    override fun mapFromDomainModel(domainModel: ResultDomain): Result {
        TODO("NO NEED TO IMPLEMENT.")
    }
}