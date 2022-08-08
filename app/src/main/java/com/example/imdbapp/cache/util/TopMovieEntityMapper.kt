package com.example.imdbapp.cache.util

import com.example.imdbapp.cache.models.TopMovieEntity
import com.example.imdbapp.domain.model.TopMovie
import com.example.imdbapp.domain.util.DomainMapper

class TopMovieEntityMapper : DomainMapper<TopMovieEntity,TopMovie> {
    override fun mapToDomainModel(model: TopMovieEntity): TopMovie {
        return TopMovie(
            crew = model.crew,
            fullTitle =  model.fullTitle,
            id =  model.id,
            image = model.image,
            imdbContentRating =  model.imdbContentRating,
            imdbRating = model.imdbRating,
            title =  model.title,
            type =  model.type,
            year =  model.year,
            rank = model.rank,
            rankUpDown =  model.rankUpDown,
        )
    }


    fun mapToDomainList(listModel : List<TopMovieEntity>?) : List<TopMovie>{
        var returnList = arrayListOf<TopMovie>()
        listModel?.forEach {
            it?.let {
                returnList.add(mapToDomainModel(it))
            }
        }
        return returnList
    }

    fun mapFromDomainList(listModel : List<TopMovie>?) : List<TopMovieEntity> {
        var returnList = arrayListOf<TopMovieEntity>()
        listModel?.forEach {
            it?.let {
                returnList.add(mapFromDomainModel(it))
            }
        }
        return returnList
    }

    override fun mapFromDomainModel(domainModel: TopMovie): TopMovieEntity {
        return TopMovieEntity(
            crew = domainModel.crew,
            fullTitle =  domainModel.fullTitle,
            id =  domainModel.id,
            image = domainModel.image,
            imdbContentRating =  domainModel.imdbContentRating,
            imdbRating = domainModel.imdbRating,
            title =  domainModel.title,
            type =  domainModel.type,
            year =  domainModel.year,
            rank = domainModel.rank,
            rankUpDown =  domainModel.rankUpDown,

        )
    }
}