package com.example.imdbapp.presentation.ui.explore

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.collectAsState
import com.example.imdbapp.presentation.ui.explore.components.ExploreLoadingView
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel,
    onNavigateToMovieScreen : (String) -> Unit,
){
    val onload = viewModel.onLoad.value

    val isRefreshing = viewModel.isRefreshing.collectAsState()




    if (!onload){
        viewModel.onLoad.value = true
        viewModel.onTriggerEvent(
            ExploreEvent.GetTopAwarded()
        )
        viewModel.onTriggerEvent(
            ExploreEvent.GetTopMovies()
        )
    }
    val loading = viewModel.loading.value
    val topMovies = viewModel.topMovies.value
    val oscarMovies = viewModel.oscarMovies.value






    if (!loading){
    ExploreView(
        exploreList = topMovies, topHorizontalRow = oscarMovies, onNavigateToMovieScreen = onNavigateToMovieScreen, isRefreshing = isRefreshing.value,
    refreshScreen = viewModel::refresh
        )}
    AnimatedVisibility(visible = loading,
    enter = fadeIn(),
        exit = fadeOut()
        ) {
        ExploreLoadingView()
    }
}