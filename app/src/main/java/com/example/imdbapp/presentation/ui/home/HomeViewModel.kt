package com.example.imdbapp.presentation.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.imdbapp.domain.model.advanced_search.ResultDomain
import com.example.imdbapp.interactors.advanced_search.GetAdvancedSearchResults
import com.example.imdbapp.interactors.categories.GetMoviesByGenre
import com.example.imdbapp.network.model.advanced_search.Result
import com.example.imdbapp.presentation.ui.genres.GenresEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    val getMoviesByGenre: GetMoviesByGenre
):ViewModel(){
    val categoryResults = mutableStateOf<List<ResultDomain>>(listOf())

    val errorMessage = mutableStateOf("")

    val loading = mutableStateOf(false)

    fun onTriggerEvent(event :GenresEvent) {
        when (event) {
            is GenresEvent.GetByGenre -> {
                getMoviesByGenre.execute(
                    genre = event.genre
                ).onEach {
                    loading.value = it.loading
                    it.data?.let { data ->
                        categoryResults.value = data
                    }
                    it.error?.let { error ->
                        errorMessage.value = error
                    }
                }
            }
        }
    }
}