package com.example.imdbapp.network.model.reviews

import com.example.imdbapp.domain.model.Reviews
import com.example.imdbapp.domain.util.DomainMapper

class ReviewsDtoMapper : DomainMapper<ReviewsModel,Reviews> {
    override fun mapToDomainModel(model: ReviewsModel): Reviews {
        return Reviews(
            errorMessage = model.errorMessage,
            fullTitle = model.fullTitle,
            imDbId = model.imDbId,
            items = model.items,
            title = model.title,
            type = model.type,
            year = model.year
        )
    }

    override fun mapFromDomainModel(domainModel: Reviews): ReviewsModel {
        TODO("Noting to implement.")
    }
}