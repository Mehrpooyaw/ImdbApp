package com.example.imdbapp.network.model

import com.example.imdbapp.domain.model.Movie
import com.example.imdbapp.domain.util.DomainMapper
import com.example.imdbapp.network.model.movie.MovieModel

class MovieDtoMapper: DomainMapper<MovieModel, Movie> {
    override fun mapToDomainModel(model: MovieModel): Movie {
        return Movie(
            actorList = model.actorList,
            awards = model.awards,
            boxOffice = model.boxOffice,
            companies = model.companies,
            companyList = model.companyList ,
            contentRating = model.contentRating ,
            countries = model.countries ,
            countryList = model.countryList ,
            directorList = model.directorList ,
            directors = model.directors ,
            errorMessage = model.errorMessage ,
            fullCast = model.fullCast ,
            fullTitle = model.fullTitle ,
            genreList = model.genreList ,
            genres = model.genres ,
            id = model.id ,
            imDbRating = model.imDbRating ,
            imDbRatingVotes = model.imDbRatingVotes ,
            image = model.image ,
            images = model.images ,
            keywordList = model.keywordList ,
            keywords = model.keywords ,
            languageList = model.languageList ,
            languages = model.languages ,
            metacriticRating = model.metacriticRating ,
            originalTitle = model.originalTitle ,
            plot = model.plot ,
            plotLocal = model.plotLocal ,
            plotLocalIsRtl = model.plotLocalIsRtl ,
            posters = model.posters ,
            ratings = model.ratings ,
            releaseDate = model.releaseDate ,
            runtimeMins = model.runtimeMins ,
            runtimeStr = model.runtimeStr ,
            similars = model.similars ,
            starList = model.starList ,
            stars = model.stars ,
            tagline = model.tagline ,
            title = model.title ,
            trailer = model.trailer ,
            tvEpisodeInfo = model.tvEpisodeInfo ,
            tvSeriesInfo = model.tvSeriesInfo ,
            type = model.type ,
            writerList = model.writerList ,
            writers = model.writers ,
            year = model.year ,
        )
    }

    override fun mapFromDomainModel(domainModel: Movie): MovieModel {
        TODO("Not yet implemented")
    }
}