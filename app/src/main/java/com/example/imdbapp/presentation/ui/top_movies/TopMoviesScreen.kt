package com.example.imdbapp.presentation.ui.top_movies

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.imdbapp.domain.model.advanced_search.TopMovieType
import com.example.imdbapp.presentation.theme.AlmostSilver
import com.example.imdbapp.presentation.theme.RedSunsetGradient
import com.example.imdbapp.presentation.theme.TitaniumGradient
import com.example.imdbapp.presentation.ui.explore.ExploreEvent
import com.example.imdbapp.presentation.ui.loading_and_default_views.DefaultErrorView

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopMoviesScreen(
    modifier : Modifier = Modifier,
    type : String,
    viewModel: TopMovieViewModel,
    popUp : () -> Unit,
    onMovieClicked : (String) -> Unit
){
    val loading = viewModel.loading.value
    val onLoad = viewModel.onLoad.value
    val topMovies = viewModel.topMovies.value
    val errorMessage = viewModel.errorMessage.value

    if (!onLoad){
        viewModel.onLoad.value = true
        viewModel.onTriggerEvent(ExploreEvent.GetTopAwarded(TopMovieType.valueOf(type)))
    }
    if (loading){
        AnimatedVisibility(
            visible = loading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize()

                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Spacer(
                    modifier = modifier
                        .blur(70.dp)
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(

                                    TitaniumGradient[0].copy(0.9f),
                                    TitaniumGradient[1].copy(0.9f),

                                    )

                            )
                        )
                )
                Card(
                    modifier = modifier
                        .fillMaxWidth(0.5f)
                        .aspectRatio(1f),
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20)
                ) {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    RedSunsetGradient

                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        CircularProgressIndicator(
                            modifier = modifier.size(70.dp),
                            strokeWidth = 5.dp,
                            color = AlmostSilver
                        )
                    }
                }
            }
        }
    }
    if (!loading && onLoad){
        TopMovieView(topMovie = topMovies, popUp = popUp, onMovieClicked = onMovieClicked , title =TopMovieType.valueOf(type).str )
    }
    if (!loading && topMovies.isEmpty() && errorMessage.isNotEmpty()){
        Box(Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
            DefaultErrorView(error = errorMessage, onBackPressed =popUp)
        }
    }


}