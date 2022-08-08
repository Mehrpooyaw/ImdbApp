package com.example.imdbapp.presentation.ui.top_movies

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.domain.model.advanced_search.ResultDomain
import com.example.imdbapp.interactors.top_movies.GetTopMovies
import com.example.imdbapp.presentation.ui.explore.ExploreEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TopMovieViewModel
@Inject
constructor(
    val getTop: GetTopMovies
): ViewModel(){
    val loading = mutableStateOf(true)

    val onLoad = mutableStateOf(false)

    val topMovies = mutableStateOf<List<ResultDomain>>(ArrayList())

    val errorMessage = mutableStateOf("")


    fun onTriggerEvent(event : ExploreEvent){
        when (event){
            is ExploreEvent.GetTopAwarded -> {
                getTop.execute(
                    topMovieType = event.type
                ).onEach { dataState ->
                    loading.value = dataState.loading
                    dataState.data?.let {
                        if (it.size <51) {
                            topMovies.value = it
                        }else{
                            topMovies.value = it.shuffled().subList(0,50)
                        }
                    }
                    dataState.error?.let {
                        errorMessage.value = it
                    }


                }.launchIn(viewModelScope)
            }
        }

    }

}