package com.example.imdbapp

import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.datastore.dataStore
import com.example.imdbapp.cache.AppDao
import com.example.imdbapp.cache.models.ApiKey
import com.example.imdbapp.datastore.SettingsDataStore
import com.example.imdbapp.interactors.settings.SettingsInteractor
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.ui.ImdbApp
import com.example.imdbapp.presentation.ui.util.LockScreenOrientation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@AndroidEntryPoint
@ExperimentalPagerApi
@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var dataStore : SettingsDataStore

    @Inject
    lateinit var appDao : AppDao



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {
            IMdbAppTheme(
                darkTheme = dataStore.isDark.value
            ) {
                LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

                val uiController = rememberSystemUiController()
                uiController.setNavigationBarColor(
                    MaterialTheme.colors.background,
                    darkIcons = MaterialTheme.colors.isLight
                )
                ImdbApp(
                    toggleTheme = dataStore::toggleTheme,
                    addToApiKeys = {},
                    removeFromApiKeys = {},
                    exitApp = {
                        finishAndRemoveTask()
                    }
                )

            }
        }
    }



}




fun getDefaultApiKeys(appDao : AppDao,dataStore: SettingsDataStore){
    MainScope().launch {
        appDao.insertApiKeys(
            listOf(
                ApiKey(id = 1, stringId = "k_iu5o6g6a",name = "Default One"),
                ApiKey(2, "k_e5ncxzpm",name = "Default Two"),
                ApiKey(3, "k_ujvwraib",name = "Default Three"),
                ApiKey(4, "k_7w5zq0a5",name = "Default Four"),
            )
        )
    }
    dataStore.firstTimeAppLaunched()

}


