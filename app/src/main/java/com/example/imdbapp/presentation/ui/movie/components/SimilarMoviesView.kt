package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import com.example.imdbapp.network.model.movie.Similar
import com.example.imdbapp.presentation.theme.AlmostDarkGray
import com.example.imdbapp.presentation.theme.AlmostYellow
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.ui.components.CustomTitleText
import com.example.imdbapp.util.FakeData
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.placeholder

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SimilarMoviesRowList(
    modifier : Modifier = Modifier,
    similarMovies : List<Similar>,
    onMovieClicked : (String?) -> Unit
    ){
    Column(modifier.padding(vertical = 5.dp)) {
        CustomTitleText(text = "MORE LIKE THIS ...",modifier = modifier.padding(start = 15.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
        ) {
            items(similarMovies) {
                SimilarMovieCard(similarMovie = it, onMovieClicked = onMovieClicked)
            }

        }
    }

}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SimilarMovieCard(
    modifier : Modifier = Modifier,
    similarMovie : Similar,
    onMovieClicked: (String?) -> Unit
){
    val painter = rememberImagePainter(data = similarMovie.image, builder = {
        transformations(
            BlurTransformation(
                radius = 9f, context = LocalContext.current, sampling = 4f))})
    val isImageLoaded = remember { mutableStateOf(false) }
    val image = rememberImagePainter(data = similarMovie.image,)
    isImageLoaded.value = painter.state is ImagePainter.State.Success
    Button (
        modifier = modifier
            .width(200.dp)
            .aspectRatio(7 / 9f)
            .padding(horizontal = 5.dp)
            .clickable {
                //TODO : onClick
            }
            ,
        shape = RoundedCornerShape(5),
        border = BorderStroke(width = 0.5.dp,color = AlmostDarkGray),
        onClick = {
            onMovieClicked(similarMovie.id)
        },
        contentPadding = PaddingValues(0.dp)
        

            ) {
        Box(modifier = modifier.fillMaxSize()){
            Box(modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.CenterEnd) {
                Image(
                    painter = painter


                    ,
                    contentDescription = "movie_similar_image",
                    modifier = modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    alpha = 0.8f
                )
                Box(
                    modifier = modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight()
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color.Transparent,
                                    MaterialTheme.colors.surface,
                                )
                            )
                        ),)
                Box(modifier = modifier.fillMaxSize()){
                   Column(modifier = modifier
                       .fillMaxSize()
                       .padding(bottom = 5.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly){
                      Card(
                          modifier = modifier
                              .padding(5.dp)
                              .fillMaxWidth(0.7f)
                              .aspectRatio(0.77f)
                              .placeholder(
                                  visible = !isImageLoaded.value,
                                  shape = RoundedCornerShape(5),
                                  color = Color.LightGray,
                                  highlight = PlaceholderHighlight.fade()
                              ),
                          shape = RoundedCornerShape(5)
                      ){
                          Image(painter =
                          image,
                              contentDescription = "movie_similar_image",
                              contentScale = ContentScale.FillBounds,
                              modifier = modifier.fillMaxSize()
                              )
                      }
                       similarMovie.title?.let {
                           Text(similarMovie.title,color = Color.White, maxLines = 1,
                               fontFamily = FontFamily.Serif,
                               fontWeight = FontWeight.Thin, overflow = TextOverflow.Ellipsis, modifier = modifier.padding(horizontal = 3.dp))
                       }
                       similarMovie.imDbRating?.let {
                           if (it != "") {
                               Row(verticalAlignment = Alignment.CenterVertically) {
                                   Text(similarMovie.imDbRating, color = AlmostYellow)
                                   Text(" of 10", color = Color.Gray)
                               }
                           }
                       }

                   }
                }
            }
        }
    }

}



@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SimilarMoviesRowListPreview(){
    IMdbAppTheme {
        Scaffold {
            FakeData.SampleMovie.similars?.let { SimilarMoviesRowList(similarMovies = it){} }

        }
    }
}


@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SimilarMovieCardPreview() {
    IMdbAppTheme {

        FakeData.SampleMovie.similars?.get(0)?.let { SimilarMovieCard(similarMovie = it){} }
    }
}