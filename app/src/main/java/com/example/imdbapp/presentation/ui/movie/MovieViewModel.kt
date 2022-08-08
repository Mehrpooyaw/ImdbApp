package com.example.imdbapp.presentation.ui.movie

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.cache.AppDao
import com.example.imdbapp.cache.models.MovieEntity
import com.example.imdbapp.datastore.SettingsDataStore
import com.example.imdbapp.domain.model.Movie
import com.example.imdbapp.domain.model.Reviews
import com.example.imdbapp.interactors.favorites.GetFavoritesMovies
import com.example.imdbapp.interactors.movie.GetMovie
import com.example.imdbapp.interactors.settings.SettingsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


const val STATE_MOVIE_KEY = "movie.state.movie.key"


@HiltViewModel
class MovieViewModel
@Inject
constructor(
    private val getMovie: GetMovie,
    private val state : SavedStateHandle,
    private val setting : SettingsDataStore,
    private val appDao : AppDao,
    private val getFavoritesMovies: GetFavoritesMovies
):ViewModel() {

        val movie : MutableState<Movie?> = mutableStateOf(null)

        val loading = mutableStateOf(false)

        val onLoad = mutableStateOf(false)

        var images = mutableListOf<String>()

        val isFavorite = mutableStateOf(false)


        var errorMessage = mutableStateOf("")


        init {
            state.get<String>(STATE_MOVIE_KEY)?.let { movieId->
                onTriggerEvent(MovieEvent.GetMovieEvent(movieId))

            }

        }

    fun onTriggerEvent(event: MovieEvent) {
        viewModelScope.launch {
            when(event){
                is MovieEvent.GetMovieEvent -> {
                    if(movie.value == null){
                        getMovie(event.id)
                    }
                }

            }
        }

    }
    fun onFavoriteButtonClicked(movie : Movie){
        viewModelScope.launch {
            if (isFavorite.value) {
                isFavorite.value = false
                movie.id?.let { removeFromFavorites(it) }
            } else {
                try {
                    insertToFavorite(movie)
                    isFavorite.value = true
                } catch (e: Exception) {
                    Log.e("appDebug", e.message ?: "Unknown Error.")
                }
            }
        }
    }
    private suspend fun insertToFavorite(movie: Movie){
        getFavoritesMovies.insertToFavorites(movie = movie)
    }

    private suspend fun removeFromFavorites(movieId : String){
        getFavoritesMovies.removeFromFavorites(movieId = movieId)
    }


     private fun getMovie(id: String) {
        getMovie.execute(imdbId = id, apiKey = setting.apiKey.value).onEach { dataState ->
            loading.value = dataState.loading
            dataState.data?.let { data ->
                movie.value = data
                state.set(STATE_MOVIE_KEY, data.id)
                if (data.errorMessage != null) {
                    errorMessage.value = data.errorMessage
                }
                isFavorite.value = movie.value?.id?.let { appDao.isMovieExistInFavorites(it) } == true
                getImages(movie = data)
            }

            dataState.error?.let { error ->
                errorMessage.value = error
            }
        }.launchIn(viewModelScope)

    }






    private fun getImages(movie : Movie){
        movie.image?.let { images.add(it) }
        val posters = movie.posters
        if (posters != null) {
            posters.posters?.let {
                if (it.size > 4) {
                    for (i in it.subList(0,3)){
                        i.link?.let { link ->
                            images.add(link)
                        }
                    }
                } else {
                    for (i in it) {
                        i.link?.let { link ->
                            images.add(link)
                        }
                    }
                }
            }
        }

    }
}