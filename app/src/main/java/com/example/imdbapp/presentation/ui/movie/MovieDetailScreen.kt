package com.example.imdbapp.presentation.ui.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import com.example.imdbapp.network.model.movie.Actor
import com.example.imdbapp.presentation.ui.loading_and_default_views.DefaultErrorView
import com.example.imdbapp.presentation.ui.movie.components.MovieLoadingView
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    movieId : String?,
    viewModel : MovieViewModel,
    onPersonClicked : (String?) -> Unit,
    onNavigateToActorsScreen : (List<Actor>?) -> Unit,
    onMovieClicked : (String?) -> Unit,
    onNavigateToCompanyScreen : (String?) -> Unit,
    onNavigateToReviewScreen : (String) -> Unit,
    onNavigateToWikipediaScreen : () -> Unit,
    onNavigateToTrailerScreen : (String) -> Unit,
    onBackPressed : () -> Unit
){
    if (movieId == null){
        // TODO : Show Invalid Movie.
    }
    else {
        // Fire a one-off event to get the movie from API.
        val onLoad = viewModel.onLoad.value
        if (!onLoad){
            viewModel.onLoad.value = true
            viewModel.onTriggerEvent(MovieEvent.GetMovieEvent(movieId))
        }
        val loading = viewModel.loading.value

        val movie = viewModel.movie.value

        val images = viewModel.images

        val isFavorite = viewModel.isFavorite.value
        val scaffoldState = rememberScaffoldState()
        

        val errorMessage = viewModel.errorMessage.value

            Scaffold(
                scaffoldState = scaffoldState
            ) {
                Box(modifier = Modifier.fillMaxSize()){
                    if (loading && movie == null){
                        MovieLoadingView(
                            onBackPressed = onBackPressed
                        )
                    }else if(!loading && movie == null && onLoad){
                        DefaultErrorView(error = errorMessage, onBackPressed = onBackPressed )

                    }else if (movie != null &&!loading && movie.id == null){
                        DefaultErrorView(error = errorMessage, onBackPressed = onBackPressed )

                    }
                    else {
                        movie?.let { movie ->
                            MovieView(movie = movie,
                                onPersonClicked = onPersonClicked,
                                images = images,
                                onMovieClicked = onMovieClicked,
                                onNavigateToActorsScreen = onNavigateToActorsScreen,
                                onNavigateToReviewsScreen = onNavigateToReviewScreen,
                                onNavigateToCompanyScreen = onNavigateToCompanyScreen,
                                onNavigateToWikipediaScreen = onNavigateToWikipediaScreen,
                                onNavigateToTrailerScreen = onNavigateToTrailerScreen,
                                onBackPressed = onBackPressed,
                                isFavorite = isFavorite,
                                onFavoriteButtonClicked = viewModel::onFavoriteButtonClicked
                                )
                        }
                    }
                }


        }



    }

}