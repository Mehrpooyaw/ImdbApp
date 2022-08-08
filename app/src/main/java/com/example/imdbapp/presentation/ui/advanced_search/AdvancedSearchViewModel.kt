package com.example.imdbapp.presentation.ui.advanced_search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.interactors.advanced_search.GetAdvancedSearchResults
import com.example.imdbapp.network.model.advanced_search.AdvancedSearchOptions
import com.example.imdbapp.network.model.advanced_search.AdvancedSearchResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AdvancedSearchViewModel
@Inject
constructor(
    val getAdvancedSearchResults: GetAdvancedSearchResults

) : ViewModel(){

    var searchType = mutableStateOf(AdvancedSearchType.SearchAll)
    var query = mutableStateOf("")
    var imdbRatingFrom = mutableStateOf("1.0")
    var imdbRatingTo = mutableStateOf("10.0")
    var fromDate = mutableStateOf("1900-01-01")
    var toDate = mutableStateOf("2023-12-31")
    var genres = mutableListOf<String>()
    val loading = mutableStateOf(false)
    val errorMessage = mutableStateOf("")
    var goToResults = mutableStateOf(false)

    val results : MutableState<AdvancedSearchResults?> = mutableStateOf(null)

    init {
//        resetToDefault()
    }


    fun onTriggerEvent() {
        getAdvancedSearchResults.execute(
            options = prepareSearchRequest()
        ).onEach {
            loading.value = it.loading
            it.data?.let { data ->
                results.value = data
                Log.d("appDebug","We are in onTrigger : not Null. data is : ${data.queryString}")
                goToResults.value = true
            }
            it.error?.let { error ->
                errorMessage.value = error
            }


        }.launchIn(viewModelScope)
    }


    private fun prepareSearchRequest() : AdvancedSearchOptions{
        var request = AdvancedSearchOptions(
            type = searchType.value,
        )
            request = request.copy(query = query.value)


            request = request.copy(dateFrom = fromDate.value)


            request = request.copy(dateTo = toDate.value)


            request = request.copy(imdbRateFrom = imdbRatingFrom.value)


            request = request.copy(imdbRateTo = imdbRatingTo.value)

            request = request.copy(genres = genres)

        return request
    }


    fun resetToDefault(){
        searchType.value = AdvancedSearchType.SearchAll
        query.value = ""
        imdbRatingFrom.value = ""
        imdbRatingTo.value = ""
        fromDate.value = ""
        toDate.value = ""
        genres.clear()
    }

    fun onGenreCheckedChange(
        bool : Boolean,
        str : String
    ){
        if (bool){
            addGenreToList(str)
        }else{
            removeGenreToList(str)
        }
    }


    private fun addGenreToList(genre : String){
        genres.add(genre)
    }

    private fun removeGenreToList(genre : String){
        genres.remove(genre)
    }


    fun setByDropMenu(
        type : DropMenuType,
        str : String
    ){
        when(type){
            DropMenuType.DateFrom -> {
                setDateFrom(str)
            }
            DropMenuType.DateTo -> {
                setDateTo(str)
            }
            DropMenuType.ImdbRatingFrom -> {
                setRatingFrom(str)
            }
            DropMenuType.ImdbRatingTo -> {
                setRatingTo(str)
            }
        }

    }
    
    
    fun setTitle(str : String){
        query.value = str
    }
    private fun setRatingFrom(str : String){
        imdbRatingFrom.value = str
    }
    private fun setRatingTo(str : String){
        imdbRatingTo.value = str
    }
    private fun setDateFrom(str : String){
        fromDate.value = str
    }
    private fun setDateTo(str : String){
        toDate.value = str
    }
    fun setAdvancedSearchType(type : AdvancedSearchType){
        searchType.value = type
    }



}


enum class DropMenuType(){
    ImdbRatingFrom,
    ImdbRatingTo,
    DateFrom,
    DateTo,
}