package com.example.imdbapp.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.imdbapp.network.model.listItemMovies.Item
import com.example.imdbapp.presentation.theme.AlmostYellow
import com.example.imdbapp.presentation.theme.TitaniumGradient
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
fun HomeMovie21x9Pager(
    modifier : Modifier = Modifier,
    movies : List<Item>,
    onNavigateToMovieDetail : (String) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = movies.size/2)
    Column(
        modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()) {
        HorizontalPager(
            modifier = modifier.fillMaxWidth(),
            count = movies.size,
            contentPadding = PaddingValues(horizontal = 10.dp),
            itemSpacing = 10.dp,
            state = pagerState
        ) { page ->
            HomeMovie21x9CardButton(movie = movies[page], onClick = onNavigateToMovieDetail)

        }
        HorizontalPagerIndicator(pagerState = pagerState,
        modifier = modifier
            .padding(top = 10.dp)
            .align(Alignment.CenterHorizontally),
            activeColor = AlmostYellow,
            inactiveColor = MaterialTheme.colors.onBackground,
            )
    }
}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeMovie21x9CardButton(
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
            .aspectRatio(19 / 9f),
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
        Box(modifier.fillMaxSize()) {
            Image(
                painter = painter, contentDescription = "home_21_9_card_movie",
                modifier = modifier
                    .fillMaxSize()
                    .blur(5.dp),
                contentScale = ContentScale.FillBounds,
                alpha = 1f
            )

            Row(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color.LightGray.copy(alpha = 0.8f),
                                Color.Gray.copy(alpha = 0.85f),
                                Color.DarkGray.copy(alpha = 0.85f),
                                Color.DarkGray.copy(alpha = 0.85f),
                                Color.Gray.copy(alpha = 0.85f),
                            ),


                            tileMode = TileMode.Mirror
                        )
                    )
                    .padding(horizontal = 15.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = modifier
                        .fillMaxHeight()
                        .aspectRatio(0.7f)
                        .placeholder(
                            visible = !isImageLoaded.value,
                            shape = RoundedCornerShape(17),
                            color = Color.LightGray,
                            highlight = PlaceholderHighlight.fade()
                        ),
                    shape = RoundedCornerShape(17),
                    elevation = 10.dp,
                ) {
                    Image(
                        modifier = modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds,
                        painter = painter,
                        contentDescription = "home_21_9_card_movie"
                    )

                }
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                        movie.title?.let {
                            if (it != "") {
                                Text(
                                    text = it,
                                    
                                    fontFamily = FontFamily.SansSerif,
                                    color = Color.Cyan
                                )
                            }
                        }
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {


                            movie.imDbRating?.let {
                                if (it != "") {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(it, color = Color.Yellow)
                                        Text(" of 10", color = Color.White)
                                    }
                                }
                            }
                            movie.imDbRatingCount?.let {
                                if (it != "") {
                                    Text(
                                        text = "$it voters",

                                        fontFamily = FontFamily.SansSerif,
                                        color = Color.Green
                                    )
                                }
                            }
                        }
                    movie.crew?.let {
                        if (it != "") {
                            Text(
                                text = it,
                                
                                fontFamily = FontFamily.SansSerif,
                                color = Color.White
                            )
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
fun HomeMovie21x9PagerPreview(){

    val movies = FakeData.SampleTop250
    Scaffold() {


        movies.items?.let { HomeMovie21x9Pager(movies = it.subList(0, 6)) {} }
    }
}