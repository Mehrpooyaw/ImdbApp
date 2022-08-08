package com.example.imdbapp.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.imdbapp.network.model.listItemMovies.Item
import com.example.imdbapp.presentation.theme.AlmostYellow
import com.example.imdbapp.util.FakeData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.placeholder

@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeDoubleMoviesPager(
    modifier : Modifier = Modifier,
    movies : List<Item>,
    spacerAlignment : Alignment,
    onNavigateToMovieDetail : (String) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = movies.size/2)

        Box(
            modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
                .aspectRatio(0.7f)
        ) {
            Column(
                modifier
                    .fillMaxWidth()) {
                HorizontalPager(
                    modifier = modifier.fillMaxWidth(),
                    count = movies.size,
                    contentPadding = PaddingValues(horizontal = 10.dp),
                    itemSpacing = 10.dp,
                    state = pagerState
                ) { page ->
                    HomeDoubleMoviesCardButton(
                        movie = movies[page],
                        onClick = onNavigateToMovieDetail
                    )

                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = modifier
                        .padding(top = 10.dp)
                        .align(Alignment.CenterHorizontally),
                    activeColor = AlmostYellow,
                    inactiveColor = MaterialTheme.colors.onBackground,
                )
            }
            Spacer(
                modifier = modifier
                    .align(spacerAlignment)
                    .fillMaxHeight()
                    .width(15.dp)
                    .background(
                        if (spacerAlignment == Alignment.CenterStart) {
                            Brush.horizontalGradient(
                                listOf(
                                    MaterialTheme.colors.background,
                                    MaterialTheme.colors.background.copy(alpha = 0.7f),
                                    Color.Transparent,


                                    )
                            )
                        } else {
                            Brush.horizontalGradient(
                                listOf(
                                    MaterialTheme.colors.background,
                                    Color.Transparent,


                                    ).reversed()
                            )

                        }

                    )
            )



    }
}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeDoubleMoviesCardButton(
    modifier : Modifier = Modifier,
    movie : Item,
    onClick : (String) -> Unit
){
    val isImageLoaded = remember { mutableStateOf(false) }
    val painter = rememberImagePainter(data = movie.image,)
    isImageLoaded.value = painter.state is ImagePainter.State.Success

    Button(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.7f),
        onClick = {
            movie.id?.let {
                onClick(it) }
        },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 10.dp,
            pressedElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background
        ),
        shape = RoundedCornerShape(10),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Image(
                    painter = painter,
                    contentDescription = "movie_pager_double_start",
                    modifier = modifier
                        .fillMaxSize()
                        .blur(5.dp),
                    contentScale = ContentScale.FillBounds,
                    alpha = 1f
                )
                Box(modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(

                            listOf(
                                Color.LightGray.copy(alpha = 0.8f),
                                Color.Gray.copy(alpha = 0.85f),
                                Color.DarkGray.copy(alpha = 0.85f),
                                Color.DarkGray.copy(alpha = 0.85f),
                                Color.Gray.copy(alpha = 0.85f),
                            ),
                            tileMode = TileMode.Mirror
                        )
                    )) {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(bottom = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Card(
                            modifier = modifier
                                .padding(5.dp)
                                .fillMaxHeight(0.7f)
                                .aspectRatio(0.7f)
                                .placeholder(
                                    visible = !isImageLoaded.value,
                                    shape = RoundedCornerShape(17),
                                    color = Color.LightGray,
                                    highlight = PlaceholderHighlight.fade()
                                ),
                            shape = RoundedCornerShape(17)
                        ) {
                            Image(
                                painter =
                                 painter
                                ,
                                contentDescription = "movie_pager_double_start",
                                contentScale = ContentScale.FillBounds,
                                modifier = modifier.fillMaxSize()
                            )
                        }
                        movie.title?.let {
                            Text(
                                it,
                                color = Color.White,
                                maxLines = 1,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Thin,
                                overflow = TextOverflow.Ellipsis,
                                modifier = modifier.padding(horizontal = 3.dp)
                            )
                        }
                        movie.imDbRating?.let {
                            if (it != "") {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(it, color = AlmostYellow)
                                    Text(" of 10", color = Color.LightGray)
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}



@ExperimentalPagerApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeDoubleMoviesPreview(){

    val movies = FakeData.SampleTop250
    Scaffold() {


        movies.items?.let { HomeDoubleMoviesPager(movies = it.subList(0, 6), spacerAlignment = Alignment.CenterStart) {} }
    }
}