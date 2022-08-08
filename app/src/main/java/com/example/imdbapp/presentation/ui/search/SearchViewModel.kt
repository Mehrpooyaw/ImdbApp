package com.example.imdbapp.presentation.ui.search

import android.app.appsearch.SearchResult
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.datastore.SettingsDataStore
import com.example.imdbapp.domain.model.SearchResults
import com.example.imdbapp.interactors.search.GetSearchResults
import com.example.imdbapp.network.model.search.SearchResultDto
import com.example.imdbapp.util.FakeData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Named

const val STATE_KEY_RESULT = "search.state.search.key"

const val STATE_KEY_QUERY = "search.state.query.key"

@HiltViewModel
class SearchViewModel
    @Inject
constructor(
        private val getResults : GetSearchResults,
        private val setting : SettingsDataStore,
        private val savedStateHandle : SavedStateHandle
    ) : ViewModel() {

    val query = mutableStateOf("")
    val searchResult : MutableState<SearchResults?> = mutableStateOf(null)
    val loading = mutableStateOf(false)
    val searchType = mutableStateOf(SearchType.Movie)
    val isExpanded = mutableStateOf(false)
    val movies = mutableStateOf(FakeData.SampleTop250.items?.shuffled()?.subList(0,5))
    val onLoad = mutableStateOf(false)
    val isOnError = mutableStateOf(false)
    var errorMessage = mutableStateOf("")
    fun onTriggerEvent(event : SearchEvent){
        when(event){
            is SearchEvent.NewSearchEvent -> {
                newSearch()
            }

        }
    }


    fun newSearch(

    ){
        resetSearchState()
        getResults.execute(
            apiKey = setting.apiKey.value,
            searchType = searchType.value,
            query = query.value
        ).onEach { dataState ->
        loading.value = dataState.loading
        dataState.data?.let {
            searchResult.value = it
            isOnError.value = false
            it.errorMessage?.let { error ->
                if (error.isNotBlank()) {
                    errorMessage.value = error
                    isOnError.value = true
                }
            }

        }
        dataState.error?.let { errorM ->
            Log.e("AppDebug", errorM)
            errorMessage.value = errorM
            isOnError.value = true
        }

        }.launchIn(viewModelScope)

    }


    fun resetSearchState(){
        isOnError.value = false
        errorMessage.value = ""
        searchResult.value = null
    }

    fun setQuery(q : String){
        isOnError.value = false
        errorMessage.value =  ""
        this.query.value = q
        this.isExpanded.value = query.value.isNotEmpty()

    }

    fun setSearchType(searchType : SearchType){
        this.searchType.value = searchType
    }
}

