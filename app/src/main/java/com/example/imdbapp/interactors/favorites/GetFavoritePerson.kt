package com.example.imdbapp.interactors.favorites

import com.example.imdbapp.cache.AppDao
import com.example.imdbapp.cache.models.MovieEntity
import com.example.imdbapp.cache.models.PersonEntity
import com.example.imdbapp.domain.data.DataState
import com.example.imdbapp.domain.model.Movie
import com.example.imdbapp.domain.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFavoritePerson(
    val appDao : AppDao
) {
    fun execute() : Flow<DataState<List<PersonEntity>>> = flow{
        try {
            emit(DataState.loading())
            emit(DataState.success(appDao.getFavoritePerson()))

        }catch (e:Exception){
            emit(DataState.error(e.message?:"Unknown error."))
        }
    }


    suspend fun removeFromFavorites(id : String){
        appDao.removeMovieFromFavorites(id)
    }

    suspend fun insertToFavorites(person : Person){
        appDao.insertFavoritePerson(PersonEntity(
            id = person.id ?: "0",
            name = person.name,
            image = person.image,
            role = person.role
        ))
    }
}