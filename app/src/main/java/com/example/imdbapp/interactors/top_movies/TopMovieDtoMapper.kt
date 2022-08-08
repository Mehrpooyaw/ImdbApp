package com.example.imdbapp.interactors.top_movies

import com.example.imdbapp.cache.models.TopMovieEntity
import com.example.imdbapp.domain.model.TopMovie
import com.example.imdbapp.domain.util.DomainMapper
import com.example.imdbapp.network.model.listItemMovies.Item
import com.example.imdbapp.network.model.listItemMovies.ListOfItems

class TopMovieDtoMapper : DomainMapper<Item, TopMovie> {
    override fun mapToDomainModel(model: Item): TopMovie {
        return TopMovie(
            crew = model.crew,
            fullTitle =  model.fullTitle,
            id =  model.id ?: "",
            image = model.image,
            imdbContentRating =  model.imDbRatingCount,
            imdbRating = model.imDbRating,
            title =  model.title,
            year =  model.year,
            rank = model.rank,
            rankUpDown =  null,
        )
    }
    fun mapToDomainList(listModel : List<Item>?) : List<TopMovie>{
        var returnList = arrayListOf<TopMovie>()
        listModel?.forEach {
            it?.let { item ->
                returnList.add(mapToDomainModel(item))
            }
        }
        return returnList
    }

    fun mapFromDomainList(listModel : List<TopMovie>) : List<Item>{
        var returnList = arrayListOf<Item>()
        listModel.forEach {
            returnList.add(mapFromDomainModel(it))
        }
        return returnList
    }

    override fun mapFromDomainModel(domainModel: TopMovie): Item {
        return Item(
            crew = domainModel.crew,
            fullTitle =  domainModel.fullTitle,
            id =  domainModel.id,
            image = domainModel.image,
            imDbRatingCount =  domainModel.imdbContentRating,
            imDbRating = domainModel.imdbRating,
            title =  domainModel.title,
            year =  domainModel.year,
            rank = domainModel.rank,
        )
    }

}