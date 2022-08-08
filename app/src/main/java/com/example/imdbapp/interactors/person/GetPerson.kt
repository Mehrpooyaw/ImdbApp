package com.example.imdbapp.interactors.person

import com.example.imdbapp.domain.data.DataState
import com.example.imdbapp.domain.model.Person
import com.example.imdbapp.network.NetworkService
import com.example.imdbapp.network.model.person.PersonDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetPerson(
    private val networkService : NetworkService,
    private val dtoMapper: PersonDtoMapper
) {

    fun execute(
        apiKey : String,
        movieId : String,
    ) : Flow<DataState<Person>> = flow {
        try {
            emit(DataState.loading())
            val networkPerson = getPersonFromNetwork(apiKey,movieId)

            emit(DataState.success(data = networkPerson))

        }catch (e : Exception){
            emit(DataState.error(message = e.message?: "Unknown error."))
        }


    }

    private suspend fun getPersonFromNetwork(apiKey: String, movieId: String): Person {
        return dtoMapper.mapToDomainModel(networkService.getPersonByPersonImdbId(apiKey = apiKey, movieImdbId = movieId
        ))
    }
}