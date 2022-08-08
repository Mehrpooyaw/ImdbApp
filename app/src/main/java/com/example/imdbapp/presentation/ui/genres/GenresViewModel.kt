package com.example.imdbapp.presentation.ui.genres

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.util.fastForEachIndexed
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.domain.model.advanced_search.ResultDomain
import com.example.imdbapp.interactors.categories.GetMoviesByGenre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GenresViewModel
@Inject
constructor(
    val getMoviesByGenre: GetMoviesByGenre,


) : ViewModel() {

    val loading = mutableStateOf(true)

    val onLoad = mutableStateOf(false)

    val movies = mutableStateOf<List<ResultDomain>?>(null)

    val errorMessage = mutableStateOf("")



    fun onTriggerEvent(event: GenresEvent) {
        when (event) {
            is GenresEvent.GetByGenre -> {
                getMoviesByGenre.execute(
                    event.genre
                ).onEach {
                    loading.value = it.loading
                    it.data?.let { data ->
                        if (data.size>21) {
                            movies.value = data.shuffled().subList(0, 20)
                        }else{
                            movies.value =data
                        }
                    }
                    it.error?.let { error->
                        errorMessage.value =error
                    }
                }.launchIn(viewModelScope)
            }
        }
    }



}