package com.example.imdbapp.interactors.movie

import androidx.compose.runtime.mutableStateOf
import com.example.imdbapp.domain.data.DataState
import com.example.imdbapp.domain.model.Movie
import com.example.imdbapp.domain.model.Reviews
import com.example.imdbapp.network.NetworkService
import com.example.imdbapp.network.model.MovieDtoMapper
import com.example.imdbapp.network.model.reviews.ReviewsDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovie(
    private val networkService: NetworkService,
    private val dtoMapper: MovieDtoMapper,
) {

    fun execute(
        apiKey : String,
        imdbId : String,

    ) : Flow<DataState<Movie>> = flow {
        var networkData : Movie? = null
        try {
            emit(DataState.loading())


            val networkMovie = getMovieFromNetwork(apiKey, imdbId)

            emit(DataState.success(data = networkMovie))
            networkData = networkMovie

        }
        catch (e : Exception){
            emit(DataState.error<Movie>(message = networkData?.errorMessage ?: e.message?: "Unknown error."))
        }
    }

    private suspend fun getMovieFromNetwork(apiKey: String, imdbId: String) : Movie {
        return dtoMapper.mapToDomainModel(networkService.getMovieByImdbId(apiKey = apiKey, imdbId = imdbId))
    }




















}