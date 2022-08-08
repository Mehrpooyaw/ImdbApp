package com.example.imdbapp.presentation.ui.advanced_search

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.imdbapp.network.model.advanced_search.AdvancedSearchResults
import com.example.imdbapp.presentation.ui.loading_and_default_views.DefaultErrorView

@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdvancedSearchScreen(
    viewModel : AdvancedSearchViewModel,
    onBackPressed : () -> Unit,
    onNavigateToMovieScreen : (String) -> Unit
) {

    val title = viewModel.query.value
    var isOnResult = viewModel.goToResults.value
    val isLoading = viewModel.loading.value
    val results = viewModel.results.value
    val errorMessage = viewModel.errorMessage.value



    AnimatedVisibility(visible = results == null,
        enter = fadeIn(),
        exit = fadeOut()
    ) {


        AdvancedSearchView(
            onBackPressed = onBackPressed,
            setByDropMenu = viewModel::setByDropMenu,
            onCheckedChange = viewModel::onGenreCheckedChange,
            setTitle = viewModel::setTitle,
            title = title,
            onSearchClicked = viewModel::onTriggerEvent,
            isLoading = isLoading
        )
    }

    AnimatedVisibility(visible = results != null,
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        AdvancedResultsView(
            onNavigateToMovieScreen = onNavigateToMovieScreen,
            onBackPressed = {
                isOnResult = false
                viewModel.results.value = null
            },
            results = results

        )

    }
    if (!isLoading && results == null && errorMessage.isNotEmpty()){
        Box(Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
            DefaultErrorView(error = errorMessage, onBackPressed = {
                viewModel.errorMessage.value = ""
            })
        }
    }


}