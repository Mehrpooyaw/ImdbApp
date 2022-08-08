package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import com.example.imdbapp.domain.model.Movie
import com.example.imdbapp.presentation.theme.NeonPink
import com.example.imdbapp.presentation.ui.components.BackLikeShareTopBar
import com.example.imdbapp.util.FakeData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ImageAndBackgroundReflex(
    modifier : Modifier = Modifier,
    isFavorite : Boolean,
    onFavoriteButtonClicked : (Movie)-> Unit,
    movie : Movie,
    images :List<String?>?,onBackPressed : () -> Unit,state : ScrollState) {


    Box(
        modifier
            .fillMaxWidth()
            .aspectRatio(7 / 9.9f),
        contentAlignment = Alignment.BottomCenter


        ){
            Image(
                painter =  rememberImagePainter(data = movie.image, builder = {
                transformations(
                    BlurTransformation(
                        radius = 8f,
                        context = LocalContext.current,
                        sampling = 3f
                    )
                )
            }),
                contentDescription = "movie_image",
                modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(7 / 9.9f),
                alpha = 0.9f,
                contentScale = ContentScale.FillBounds,

                )

        Column(
            modifier
                .fillMaxWidth()
                .aspectRatio(7 / 10f)) {
            Spacer(modifier.fillMaxHeight(2/3f))
            Box(
                modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                MaterialTheme.colors.background.copy(alpha = 0.5F),
                                MaterialTheme.colors.background.copy(alpha = 0.6F),
                                MaterialTheme.colors.background.copy(alpha = 0.7F),
                                MaterialTheme.colors.background.copy(alpha = 0.8F),
                                MaterialTheme.colors.background,
                                MaterialTheme.colors.background,
                            )
                        )
                    ))
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Top
            ) {
            BackLikeShareTopBar(isFavorite = isFavorite,onBackPressed = onBackPressed,onFavoriteButtonClicked = onFavoriteButtonClicked , movie = movie)
            Card(
                elevation = 20.dp,
                shape = RoundedCornerShape(5),
                modifier = modifier
                    .graphicsLayer {
                        lerp(
                            start = 1f ,
                            stop = 0.5f,
                            fraction = (state.value.coerceIn(300,1800).toFloat() - 300) / 1000
                        ).also {
                            scaleX = it
                            scaleY = it
                        }
                        lerp(
                            start = 1f ,
                            stop = 20f,
                            fraction = (state.value.coerceIn(300,2500).toFloat() -300) / 125
                        ).also {
                            translationY = it
                        }
                    }
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
                    .aspectRatio(7 / 9.1f),

                ) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {


                            val painter = rememberImagePainter(data = movie.image)

                            Image(
                                painter = painter,
                                contentDescription = "movie_image",
                                modifier = modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.FillBounds,

                                )




                }
            }
        }
    }
}


@ExperimentalPagerApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ImAndBackRefPreview(){
    val sampleMovie = FakeData.SampleMovie
    ImageAndBackgroundReflex(isFavorite = false, images = listOf(), state = rememberScrollState(), onBackPressed = {}, onFavoriteButtonClicked = {}, movie = sampleMovie)
}