package com.example.imdbapp.cache.util

import com.example.imdbapp.cache.models.BoxOfficeMovieAllTimeEntity
import com.example.imdbapp.domain.model.BoxOfficeMovieAllTime
import com.example.imdbapp.domain.util.DomainMapper

class BoxOfficeEntityMapper: DomainMapper<BoxOfficeMovieAllTimeEntity,BoxOfficeMovieAllTime> {
    override fun mapToDomainModel(model: BoxOfficeMovieAllTimeEntity): BoxOfficeMovieAllTime {
        return BoxOfficeMovieAllTime(
            domestic = model.domestic,
            domesticLifetimeGross = model.domesticLifetimeGross,
            foreign = model.foreign,
            foreignLifetimeGross = model.foreignLifetimeGross,
            id =  model.id,
            rank = model.rank,
            title = model.title,
            worldwideLifetimeGross = model.worldwideLifetimeGross,
            year =  model.year,
        )
    }

    override fun mapFromDomainModel(domainModel: BoxOfficeMovieAllTime): BoxOfficeMovieAllTimeEntity {
        return BoxOfficeMovieAllTimeEntity(
            domestic = domainModel.domestic,
            domesticLifetimeGross = domainModel.domesticLifetimeGross,
            foreign = domainModel.foreign,
            foreignLifetimeGross = domainModel.foreignLifetimeGross,
            id =  domainModel.id,
            rank = domainModel.rank,
            title = domainModel.title,
            worldwideLifetimeGross = domainModel.worldwideLifetimeGross,
            year =  domainModel.year,
        )
    }

}