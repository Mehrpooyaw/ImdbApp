package com.example.imdbapp.interactors.movie

import com.example.imdbapp.domain.data.DataState
import com.example.imdbapp.domain.model.Reviews
import com.example.imdbapp.network.NetworkService
import com.example.imdbapp.network.model.MovieDtoMapper
import com.example.imdbapp.network.model.reviews.ReviewsDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.Exception

class GetReviews(
    private val networkService: NetworkService,
    private val reviewsDtoMapper : ReviewsDtoMapper
) {
    fun execute(movieId : String, apiKey : String): Flow<DataState<Reviews>> = flow {
        try {


            emit(DataState.loading())

            val networkReviews = getReviewsFromNetwork(apiKey, movieId)

            emit(DataState.success(networkReviews))

        }catch (e : Exception) {
            emit(DataState.error(e.message ?: "Unknown error."))
        }

    }
    private suspend fun getReviewsFromNetwork(apiKey : String, imdbId: String) : Reviews {
        return reviewsDtoMapper.mapToDomainModel(networkService.getMovieReviews(apiKey = apiKey, movieImdbId = imdbId))
    }
}
