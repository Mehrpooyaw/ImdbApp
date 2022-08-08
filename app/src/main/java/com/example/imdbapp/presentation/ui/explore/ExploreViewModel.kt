package com.example.imdbapp.presentation.ui.explore

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.domain.model.advanced_search.ResultDomain
import com.example.imdbapp.interactors.top_movies.GetTopMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel
@Inject
constructor(
    private val getTop: GetTopMovies,
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    val loading = mutableStateOf(true)

    val onLoad = mutableStateOf(false)

    val topMovies = mutableStateOf<List<ResultDomain>>(ArrayList())

    val errorMessage = mutableStateOf("")

    val oscarMovies = mutableStateOf<List<ResultDomain>>(ArrayList())






    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            onTriggerEvent(ExploreEvent.GetTopMovies())
            onTriggerEvent(ExploreEvent.GetTopAwarded())
            _isRefreshing.emit(false)
        }
    }





    fun onTriggerEvent(event : ExploreEvent){
        when (event){
            is ExploreEvent.GetTopMovies -> {
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
            is ExploreEvent.GetTopAwarded -> {
                getTop.execute(
                    topMovieType = event.type,
                ).onEach { dataState ->
                    loading.value = dataState.loading
                    dataState.data?.let {
                        if (it.size <10){
                        oscarMovies.value = it
                        }else{
                        oscarMovies.value = it.shuffled().subList(0,10)
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