package com.example.imdbapp.interactors.categories

import android.util.Log
import com.example.imdbapp.cache.AppDao
import com.example.imdbapp.cache.models.ResultEntity
import com.example.imdbapp.cache.util.ResultEntityMapper
import com.example.imdbapp.datastore.SettingsDataStore
import com.example.imdbapp.domain.data.DataState
import com.example.imdbapp.domain.model.advanced_search.ResultDomain
import com.example.imdbapp.network.NetworkService
import com.example.imdbapp.presentation.ui.advanced_search.AdvancedSearchResultDtoMapper
import com.example.imdbapp.presentation.ui.categories.components.Genres
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMoviesByGenre (
    val networkService: NetworkService,
    val settings : SettingsDataStore,
    val entityMapper : ResultEntityMapper,
    val dtoMapper : AdvancedSearchResultDtoMapper,
    val appDao: AppDao
){
    fun execute(
        genre: Genres,
        count : Int = 250
    ) : Flow<DataState<List<ResultDomain>>> = flow {
        emit(DataState.loading())
        try {
            var cacheData = getResultsFromCache(
                genre = genre,
            )
            if (cacheData.isNullOrEmpty()){
                val networkData = getResultsFromNetwork(
                    genre = genre
                )
                insertToCache(entityMapper.fromList(networkData), genre = genre)
                cacheData = networkData

            }
            emit(DataState.success(cacheData))
        } catch(e: Exception){

            Log.d("appDebug",
                DataState.error<List<ResultDomain>>(e.message ?:"Unknown error.").toString()
            )
            emit(DataState.error<List<ResultDomain>>(e.message ?:"Unknown error."))


        }
    }

    private suspend fun getResultsFromCache(genre: Genres): List<ResultDomain>{
        var list : ArrayList<ResultDomain> = ArrayList()
        appDao.getResultsByGenres(genre = genre)?.forEach {
            list.add(entityMapper.mapToDomainModel(it))
        }
        return list.toList()

    }
    private suspend fun insertToCache(list : List<ResultEntity>, genre : Genres) {
        var returnedList : ArrayList<ResultEntity> = ArrayList()
        list.forEach {
            returnedList.add(it.copy(genreType = genre))
        }
        appDao.insertAdvancedResults(returnedList.toList())
    }

    private suspend fun getResultsFromNetwork(
        count : Int = 250,
        genre: Genres

    ) : List<ResultDomain> {
        val list = networkService.getMoviesByGenre(
            apiKey = settings.apiKey.value,
            genres = genre.title,
            pageCount = count
        ).results
        return dtoMapper.toDomainList(list)

    }

}
