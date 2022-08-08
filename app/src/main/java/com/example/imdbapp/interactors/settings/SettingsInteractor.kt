package com.example.imdbapp.interactors.settings

import android.util.Log
import com.example.imdbapp.cache.AppDao
import com.example.imdbapp.cache.models.ApiKey
import com.example.imdbapp.datastore.SettingsDataStore
import com.example.imdbapp.domain.data.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class SettingsInteractor(
    private val appDao : AppDao,
    private val settings : SettingsDataStore
) {
    fun execute(

    ) : Flow<DataState<List<ApiKey>>> = flow {
        try {
            val apiKeys = getApiKeys()
            emit(DataState.success(apiKeys))

        }catch (e : Exception) {
            emit(DataState.error(e.message?:"Unkwon error"))
        }




    }

    suspend fun getApiKeyCount() : Int {
        return appDao.apiKeysCount()
    }

    fun getApiKey() : String{
        Log.d("appDebug","the apiKey in interactor is ${settings.apiKey.value}")
        return  settings.apiKey.value

    }

    fun setApiKey(apiKey: String){
        settings.setApiKey(stringId = apiKey)
        settings.apiKey.value = apiKey
    }

     suspend fun addApiKey(apiKey : ApiKey) {
        appDao.insertApiKey(apiKey = apiKey)
    }
     suspend fun removeApiKey(apiKey : String) {
        appDao.deleteApiKey(stringId = apiKey)
    }
    private suspend fun getApiKeys():List<ApiKey>{
        val apiKeys = appDao.getApiKeys()
        if (apiKeys.isEmpty()) {
            removeApiKey("first")
            getApiKeyCount()
            if (appDao.getApiKeys().isNotEmpty()){
                setApiKey(appDao.getApiKeys()[0].stringId)
            }
            return appDao.getApiKeys()
        }else{
            return apiKeys
        }
    }
}