package com.example.imdbapp.datastore

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.imdbapp.presentation.BaseApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "Settings")

@Singleton
class SettingsDataStore
@Inject
constructor(app : BaseApplication){
    private val dataStore = app.applicationContext.dataStore

    val scope = CoroutineScope(Main)
    var isDark = mutableStateOf(true)

    var apiKey = mutableStateOf("k_7w5zq0a5")

    var isFirstTime = mutableStateOf(true)

    init {
        observeDataStore()
    }


    fun setApiKey(stringId : String) {
        scope.launch {
            dataStore.edit {  preferences ->
                preferences[API_KEY] = stringId
                Log.d("appDebugApiKey","we are setting apiKey from ${preferences[API_KEY]} to $stringId")
                apiKey.value = preferences[API_KEY] ?: ""
                Log.d("appDebugApiKey","now the mutable ApiKey is ${apiKey.value}")


            }
        }
    }




    fun toggleTheme(){
        scope.launch {
            dataStore.edit { preferences ->
                val current = preferences[DARK_THEME_KEY]?: true
                preferences[DARK_THEME_KEY] = !current

            }
        }
    }


    fun firstTimeAppLaunched(){
        scope.launch {
            dataStore.edit {
                it[FIRST_TIME_LAUNCH] = false
                isFirstTime.value = false

            }
        }
    }






    private fun observeDataStore(){
        dataStore.data.onEach {
            it[DARK_THEME_KEY]?.let { isDarkTheme ->
                isDark.value = isDarkTheme
            }
            it[API_KEY]?.let {  key ->
                apiKey.value = key

            }
            it[FIRST_TIME_LAUNCH]?.let { bool ->
                isFirstTime.value = bool

            }
        }.launchIn(scope)
    }

    companion object {
        private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme_key")
        private val API_KEY = stringPreferencesKey("api_key")
        private val FIRST_TIME_LAUNCH = booleanPreferencesKey("first_time_launch")
    }

}

