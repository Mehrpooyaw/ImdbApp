package com.example.imdbapp.presentation.ui.scaffold

import androidx.compose.material.Scaffold
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.imdbapp.presentation.ui.home.components.BaseSections
import com.example.imdbapp.presentation.ui.home.components.BottomNavBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomScaffold(
    modifier : Modifier = Modifier,
    tabs : Array<BaseSections>,
    navController : NavController,
    content : @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(tabs = tabs, navController = navController)
        },
        content = {
            content()
        },

    )

}