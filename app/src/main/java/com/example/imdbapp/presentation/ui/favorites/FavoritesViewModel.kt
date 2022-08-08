package com.example.imdbapp.presentation.ui.favorites

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.cache.models.MovieEntity
import com.example.imdbapp.cache.models.PersonEntity
import com.example.imdbapp.interactors.favorites.GetFavoritePerson
import com.example.imdbapp.interactors.favorites.GetFavoritesMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel
@Inject
constructor(
    private val getFavoritesMovies: GetFavoritesMovies,
    private val getFavoritePerson: GetFavoritePerson

) :ViewModel(){
    val favoriteMovies = mutableStateOf<List<MovieEntity>>(listOf())
    val favoritePerson = mutableStateOf<List<PersonEntity>>(listOf())
    val loading = mutableStateOf(false)
    val onLoad = mutableStateOf(false)




    fun getMovie(){
        getFavoritesMovies.execute().onEach {
            loading.value = it.loading
            it.data?.let { data ->
                favoriteMovies.value = data
            }
        }.launchIn(viewModelScope)
    }

    fun getPerson(){
        getFavoritePerson.execute().onEach {
            loading.value = it.loading
            it.data?.let { data ->
                favoritePerson.value = data
            }
        }.launchIn(viewModelScope)
    }





}