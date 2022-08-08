package com.example.imdbapp.presentation.ui.reviews

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.datastore.SettingsDataStore
import com.example.imdbapp.domain.model.Reviews
import com.example.imdbapp.interactors.movie.GetReviews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val STATE_REVIEWS_KEY = "reviews.state.reviews.key"

@HiltViewModel
class ReviewsViewModel
@Inject
constructor(
    private val getReviews : GetReviews,
    private val savedStateHandle: SavedStateHandle,
    private val settingsDataStore: SettingsDataStore
) : ViewModel(){
    val reviews : MutableState<Reviews?> = mutableStateOf(null)
    val loading = mutableStateOf(false)
    val onLoad = mutableStateOf(false)





    fun onTriggerEvent(event : ReviewsEvent){
        viewModelScope.launch {
            when (event){
                is ReviewsEvent.GetReviewsEvent -> {
                    getReviews(event.id)




                }
            }
        }

    }
    private fun getReviews(id : String){
        getReviews.execute(apiKey = settingsDataStore.apiKey.value, movieId = id).onEach { dataState ->
        loading.value = dataState.loading
        dataState.data?.let {
            reviews.value = it
            savedStateHandle.set(STATE_REVIEWS_KEY,it.imDbId)

        }
        dataState.error?.let {
            Log.e("AppDebug","getReviews : ${it}")
        }

        }.launchIn(viewModelScope)
    }
}