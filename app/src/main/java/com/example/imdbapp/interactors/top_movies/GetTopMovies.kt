package com.example.imdbapp.interactors.top_movies

import android.util.Log
import com.example.imdbapp.cache.AppDao
import com.example.imdbapp.cache.models.ResultEntity
import com.example.imdbapp.cache.util.ResultEntityMapper
import com.example.imdbapp.datastore.SettingsDataStore
import com.example.imdbapp.domain.data.DataState
import com.example.imdbapp.domain.model.advanced_search.ResultDomain
import com.example.imdbapp.domain.model.advanced_search.TopMovieType
import com.example.imdbapp.network.NetworkService
import com.example.imdbapp.presentation.ui.advanced_search.AdvancedSearchResultDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTopMovies (
    val networkService: NetworkService,
    val settings : SettingsDataStore,
    val entityMapper : ResultEntityMapper,
    val dtoMapper : AdvancedSearchResultDtoMapper,
    val appDao: AppDao
){
    fun execute(
        topMovieType: TopMovieType,
        count : Int = 250
    ) : Flow<DataState<List<ResultDomain>>> = flow {
        emit(DataState.loading())
        try {
            var cacheData = getResultsFromCache(
                topMovieType = topMovieType,
            )
            if (cacheData.isNullOrEmpty()){
                val networkData = getResultsFromNetwork(
                    topMovieType = topMovieType
                )
                insertToCache(entityMapper.fromList(networkData), top = topMovieType)
                cacheData = networkData

            }
            emit(DataState.success(cacheData))
        }catch (e: Exception){

            Log.d("appDebug",
                DataState.error<List<ResultDomain>>(e.message ?:"Unknown error.").toString()
            )
            emit(DataState.error<List<ResultDomain>>(e.message ?:"Unknown error."))


        }
    }

    private suspend fun getResultsFromCache(topMovieType: TopMovieType): List<ResultDomain>{
        var list : ArrayList<ResultDomain> = ArrayList()
        appDao.getTopResults(topMovieType = topMovieType).forEach {
            if (it != null) {
                list.add(entityMapper.mapToDomainModel(it))
            }
        }
        return list.toList()

    }
    private suspend fun insertToCache(list : List<ResultEntity>,top : TopMovieType) {
        var returnedList : ArrayList<ResultEntity> = ArrayList()
        list.forEach {
            returnedList.add(it.copy(topMovieType = top))
        }
        appDao.insertAdvancedResults(returnedList.toList())
    }

    private suspend fun getResultsFromNetwork(
        count : Int = 250,
        topMovieType: TopMovieType

    ) : List<ResultDomain> {
        val list = networkService.getTops(
            apiKey = settings.apiKey.value,
            groups = topMovieType.str,
            pageCount = count
        ).results
        return dtoMapper.toDomainList(list)

    }

}

