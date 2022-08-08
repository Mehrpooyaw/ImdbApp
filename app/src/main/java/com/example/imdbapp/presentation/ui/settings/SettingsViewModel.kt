package com.example.imdbapp.presentation.ui.settings

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.cache.models.ApiKey
import com.example.imdbapp.interactors.settings.SettingsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel
    @Inject
constructor(
    val setting : SettingsInteractor
) : ViewModel() {

    val apiKeys = mutableStateOf(listOf<ApiKey>())
    val selectedApiKey = mutableStateOf("")
    val onLoad = mutableStateOf(false)

    init {
        getApiKeys()
        getSelectedApiKey()
        getApiCount()


    }




    private fun getApiCount() {
        viewModelScope.launch {
            try {
                setting.getApiKeyCount()
            }catch (e : Exception){

            }
        }

    }


    fun getSelectedApiKey() {
        selectedApiKey.value = setting.getApiKey()
    }








    fun setApiKey(apiKey : String) {
        setting.setApiKey(apiKey = apiKey)
        getSelectedApiKey()

    }



    fun addApiKey(apiKey : ApiKey) {
        viewModelScope.launch {
            setting.addApiKey(apiKey = apiKey)
            getApiKeys()
        }

    }

    fun removeApiKey(apiKey : String) {
        viewModelScope.launch {
            setting.removeApiKey(apiKey = apiKey)
            getApiKeys()

        }
    }


     fun getApiKeys(){
        setting.execute().onEach {
            it.data?.let { keys ->
                apiKeys.value = keys
                Log.d("appDebug","We are in getApiKeys, the apiKeys are : $keys")
            }
        }.launchIn(viewModelScope)
    }
}