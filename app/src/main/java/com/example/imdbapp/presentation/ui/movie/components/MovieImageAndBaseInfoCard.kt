package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imdbapp.domain.model.Movie
import com.example.imdbapp.presentation.theme.AlmostSilver
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.util.FakeData
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieImageAndBaseInfoCard(
    modifier : Modifier = Modifier,movie : Movie,
    images : List<String?>?,
    isFavorite : Boolean,
    onBackPressed : () -> Unit,
    state : ScrollState,
    onFavoriteButtonClicked : (Movie) -> Unit,
                              ){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ImageAndBackgroundReflex(
            isFavorite = isFavorite,
            images = images,
            onBackPressed = onBackPressed,
            state = state,
            onFavoriteButtonClicked = onFavoriteButtonClicked,
            movie = movie
            )
        movie.title?.let { title ->
            if (title != "") {
                Text(

                    text = title.uppercase(),
                    modifier = modifier.padding(vertical = 5.dp, horizontal = 5.dp),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.primary
                )
            }
        }
        movie.tagline?.let{ tagLine ->
            if (tagLine != "") {
                Text(
                    text = tagLine,
                    modifier = modifier.padding(horizontal = 15.dp),
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Thin,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
        Spacer(
            modifier = modifier.padding(vertical = 5.dp)
        )

        BaseDetailAndRatingBox(
            runTime = movie.runtimeStr,
            releaseDate = movie.releaseDate,
            type = movie.type,
            languages = movie.languages
        )
        Spacer(modifier.height(10.dp))
    }
}

@ExperimentalPagerApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieImageAndInfoPreview(){
    val sampleMovie = FakeData.SampleMovie
    IMdbAppTheme() {
        MovieImageAndBaseInfoCard(movie = sampleMovie, isFavorite = false, images = listOf(), state = rememberScrollState(), onBackPressed = {}, onFavoriteButtonClicked = {})
    }
}