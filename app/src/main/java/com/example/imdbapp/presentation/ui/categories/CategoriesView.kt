package com.example.imdbapp.presentation.ui.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.domain.model.advanced_search.TopMovieType
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.ui.categories.components.CategoryBestOfMenu
import com.example.imdbapp.presentation.ui.categories.components.CategoryGenresList
import com.example.imdbapp.presentation.ui.categories.components.Genres
import com.example.imdbapp.presentation.ui.loading_and_default_views.DefaultBottomPageView

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoriesView(
    modifier : Modifier = Modifier,
    onNavigateToAdvancedSearchScreen : () -> Unit,
    onTopMovieClicked : (TopMovieType) -> Unit,
    onGenreClicked : (Genres) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        CategoryBestOfMenu(onTopMovieClicked = onTopMovieClicked)
        CategoryGenresList(onGenreClicked =onGenreClicked,onNavigateToAdvancedSearchScreen = onNavigateToAdvancedSearchScreen)
        Divider(modifier = modifier.padding(vertical = 10.dp))
        DefaultBottomPageView()
    }

}



@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoriesViewPreview(){
    IMdbAppTheme() {
        Scaffold() {

            CategoriesView(onNavigateToAdvancedSearchScreen = {}, onTopMovieClicked = {}){}
        }
    }
}

