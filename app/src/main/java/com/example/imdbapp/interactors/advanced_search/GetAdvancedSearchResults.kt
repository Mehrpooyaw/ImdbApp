package com.example.imdbapp.interactors.advanced_search

import android.util.Log
import com.example.imdbapp.cache.models.ApiKey
import com.example.imdbapp.datastore.SettingsDataStore
import com.example.imdbapp.domain.data.DataState
import com.example.imdbapp.network.NetworkService
import com.example.imdbapp.network.model.advanced_search.AdvancedSearchOptions
import com.example.imdbapp.network.model.advanced_search.AdvancedSearchResults
import com.example.imdbapp.network.model.search.SearchResultsDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetAdvancedSearchResults (
    val networkService: NetworkService,
    val settings : SettingsDataStore
    ){
    fun execute(
        options : AdvancedSearchOptions
    ) : Flow<DataState<AdvancedSearchResults>> = flow {
        try {
            emit(DataState.loading())
            val networkData = getResultsFromNetwork(options = options)
            emit(DataState.success(networkData))
            networkData.errorMessage?.let {
                emit(DataState.error<AdvancedSearchResults>(it))
            }


        }catch (e: Exception){

            Log.d("appDebug",
                DataState.error<AdvancedSearchResults>(e.message ?:"Unknown error.").toString()
            )
            emit(DataState.error<AdvancedSearchResults>(e.message ?:"Unknown error."))


        }
    }

    private suspend fun getResultsFromNetwork(
        options : AdvancedSearchOptions

    ) : AdvancedSearchResults {
        Log.d("AppDebug","AdvancedSearchResults : ${options.dateFrom},${options.dateTo},${options.genres}")
        return networkService.advancedSearch(
            apiKey = settings.apiKey.value,
            title = options.query,
            rating = convertListToString(listOf(options.imdbRateFrom,options.imdbRateTo)),
            release_date = convertListToString(listOf(options.dateFrom,options.dateTo)),
            genres = convertListToString(options.genres),
            groups = options.type.str
        )

    }

}

fun convertListToString(list : List<String>) : String{
    var str = ""
    list.forEachIndexed{ index , s ->
        if (list[index] == list.last()){
            str += s
        }else {
            str += "$s,"
        }
    }
    return str
}