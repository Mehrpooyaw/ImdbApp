package com.example.imdbapp.presentation.ui.search

import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.imdbapp.network.model.search.Result
import com.example.imdbapp.util.FakeData
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalComposeUiApi
@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onNavigateToResultScreen : (Result) -> Unit,
    onNavigateToMovieScreen : (String) -> Unit
){
    val query by viewModel.query
    val loading by viewModel.loading
    val searchResults by viewModel.searchResult
    val searchType by viewModel.searchType
    val isExpanded by viewModel.isExpanded
    val movies by viewModel.movies
    var onLoad by viewModel.onLoad
    var errorMessage by viewModel.errorMessage
    var isOnError by viewModel.isOnError

    if (!onLoad){
        onLoad = true
    }

    if(!loading && errorMessage.isNotBlank()){
        isOnError = true
    }


    
    SearchView(
        movies = movies,
        recentQueries = listOf("something","something else"),
        onSearchTypeSelected = viewModel::setSearchType,
        query = query,
        loading = loading,
        searchResults = searchResults,
        searchType = searchType,
        onQueryChanged = viewModel::setQuery,
        isExpanded = isExpanded,
        onGoClicked = viewModel::newSearch,
        onNavigateToResultScreen = onNavigateToResultScreen,
        onRecentQueriesClicked = {
            viewModel.query.value = it
        },
        onNavigateToMovieScreen = onNavigateToMovieScreen,
        errorMessage = errorMessage,
        isOnError = isOnError
    )


}