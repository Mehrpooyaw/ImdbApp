package com.example.imdbapp.interactors.favorites

import com.example.imdbapp.cache.AppDao
import com.example.imdbapp.cache.models.MovieEntity
import com.example.imdbapp.domain.data.DataState
import com.example.imdbapp.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFavoritesMovies(
    val appDao : AppDao
) {
    fun execute() : Flow<DataState<List<MovieEntity>>> = flow{
        try {
            emit(DataState.loading())
            emit(DataState.success(appDao.getFavoriteMovies()))

        }catch (e:Exception){
            emit(DataState.error(e.message?:"Unknown error."))
        }
    }


    suspend fun removeFromFavorites(movieId : String){
        appDao.removeMovieFromFavorites(movieId)
    }

    suspend fun insertToFavorites(movie : Movie){
        appDao.insertFavorites(MovieEntity(
            id = movie.id ?: "0",
            title = movie.title,
            plot = movie.plot,
            image = movie.image,
            imdbRating = movie.imDbRating
        ))
    }
}