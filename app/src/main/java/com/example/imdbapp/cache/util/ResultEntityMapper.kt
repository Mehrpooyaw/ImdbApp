package com.example.imdbapp.cache.util

import com.example.imdbapp.cache.models.ResultEntity
import com.example.imdbapp.domain.model.advanced_search.ResultDomain
import com.example.imdbapp.domain.util.DomainMapper
import com.example.imdbapp.network.model.advanced_search.Result

class ResultEntityMapper : DomainMapper<ResultEntity, ResultDomain> {
    override fun mapToDomainModel(model: ResultEntity): ResultDomain {
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
            genreType = model.genreType,
            topMovieType = model.topMovieType

        )
    }

    fun fromList(list : List<ResultDomain>?) : List<ResultEntity>{
        val returnedList : ArrayList<ResultEntity> = ArrayList()
        list?.forEach {
            returnedList.add(mapFromDomainModel(it))
        }
        return returnedList.toList()
    }





    override fun mapFromDomainModel(domainModel: ResultDomain): ResultEntity {
        return ResultEntity(
            contentRating = domainModel.contentRating,
            description= domainModel.description,
            genres = domainModel.genres,
            id = domainModel.id?:"11111",
            image = domainModel.image,
            imDbRating = domainModel.imDbRating,
            imDbRatingVotes = domainModel.imDbRatingVotes,
            runtimeStr = domainModel.runtimeStr ,
            metacriticRating = domainModel.metacriticRating,
            stars = domainModel.stars,
            plot = domainModel.plot,
            title = domainModel.title,
            genreType = domainModel.genreType,
            topMovieType = domainModel.topMovieType
        )
    }
}