package com.example.imdbapp.presentation.ui.genres

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.imdbapp.domain.model.advanced_search.ResultDomain
import com.example.imdbapp.presentation.theme.AlmostSilver
import com.example.imdbapp.presentation.theme.RedSunsetGradient
import com.example.imdbapp.presentation.theme.TitaniumGradient
import com.example.imdbapp.presentation.ui.categories.components.Genres
import com.example.imdbapp.presentation.ui.home.components.HomeCategoriesVerticalPager
import com.example.imdbapp.presentation.ui.loading_and_default_views.DefaultErrorView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder

@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GenreListScreen(
    viewModel: GenresViewModel,
    genre : String?,
    onBackPressed : () -> Unit,
    onNavigateToMovieScreen : (String) ->Unit,
    modifier : Modifier = Modifier
) {
    val onLoad = viewModel.onLoad.value
    val loading = viewModel.loading.value

    if (genre != null) {
        val enumGenre = Genres.valueOf(genre)
        if (!onLoad) {
            viewModel.onLoad.value = true
            viewModel.onTriggerEvent(GenresEvent.GetByGenre(enumGenre))

        }
        val movies = viewModel.movies.value?.toList()

        if(loading){
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                CircularProgressIndicator()
            }
        }
        if ( onLoad && movies != null){
           ListScreenView(title = enumGenre.name, movies = movies, onClick = onNavigateToMovieScreen, onBackPressed = onBackPressed, imageId = enumGenre.imageId)
        }else if(!loading && onLoad && movies == null){
            Box(Modifier.fillMaxSize()) {
                DefaultErrorView(error = viewModel.errorMessage.value, onBackPressed = onBackPressed )
            }
        }

    }else{
        viewModel.errorMessage.value = "Unable to find the selected genre"
        Box(Modifier.fillMaxSize()) {
            DefaultErrorView(error = viewModel.errorMessage.value, onBackPressed = onBackPressed )
        }
    }
}


@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GenreVerticalPagerScreen(
    viewModel: GenresViewModel,
    genre : String?,
    onBackPressed : () -> Unit,
    onNavigateToMovieScreen : (String) ->Unit,
    modifier : Modifier = Modifier
) {
    val onLoad = viewModel.onLoad.value
    val loading = viewModel.loading.value

    if (genre != null) {
        val enumGenre = Genres.valueOf(genre)
        if (!onLoad) {
            viewModel.onLoad.value = true
            viewModel.onTriggerEvent(GenresEvent.GetByGenre(enumGenre))

        }
        val viewModelMovies = viewModel.movies.value?.toList()
        var movies = listOf<ResultDomain>()
        if (viewModelMovies != null) {
            if (viewModelMovies.size>7) {
                movies = viewModelMovies.shuffled().subList(0, 6)
            }else{
                movies =viewModelMovies
            }
        }

        if(loading){
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                CircularProgressIndicator()
            }
        }
        if ( onLoad && movies.isNotEmpty()){
            HomeCategoriesVerticalPager(results =movies, onClick = onNavigateToMovieScreen,loading = loading )
        }else if(!loading && onLoad && movies.isEmpty()){
            Box(Modifier.fillMaxSize()) {
                DefaultErrorView(error = viewModel.errorMessage.value, onBackPressed = onBackPressed )
            }
        }

    }else{
        viewModel.errorMessage.value = "Unable to find the selected genre"
        Box(Modifier.fillMaxSize()) {
            DefaultErrorView(error = viewModel.errorMessage.value, onBackPressed = onBackPressed )
        }
    }
}