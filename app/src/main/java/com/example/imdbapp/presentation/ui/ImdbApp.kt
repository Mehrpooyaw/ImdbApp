package com.example.imdbapp.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.imdbapp.presentation.ui.home.components.BaseSections
import com.example.imdbapp.presentation.ui.scaffold.CustomScaffold
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlin.reflect.KFunction0


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ImdbApp(
    modifier: Modifier = Modifier,
    toggleTheme: () -> Unit,
    addToApiKeys : (String) -> Unit,
    removeFromApiKeys : (String) -> Unit,
    exitApp : () -> Unit,


){
    val navController = rememberNavController()
    val tabs = remember { BaseSections.values() }
    CustomScaffold(
      tabs = tabs,navController = navController
    ){
        AppNavGraph(
            navController = navController,
            modifier = modifier,
            toggleTheme = toggleTheme,
            addToApiKeys = addToApiKeys,
            removeFromApiKeys = removeFromApiKeys,
            exitApp = exitApp,
        )
    }

}