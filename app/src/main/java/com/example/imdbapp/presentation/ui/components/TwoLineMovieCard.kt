package com.example.imdbapp.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

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
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import com.example.imdbapp.presentation.theme.AlmostDarkGray
import com.example.imdbapp.presentation.theme.AlmostYellow
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.util.FakeData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TwoLineMovieCard(
    modifier : Modifier = Modifier,
    imageContentDescription : String?,
    imageUrl : String?,
    firstLine : String?,
    secondLine : String?,
    secondLineForImdbRate : String?,
    cardAspectRatio : Float = 7/9f,
    onNavigateToMovieScreen : (String?) -> Unit,
    movieId : String?

    ) {
    val painter = rememberImagePainter(data = imageUrl, builder = {
        transformations(
            BlurTransformation(
                radius = 9f, context = LocalContext.current, sampling = 4f
            )
        )
    })

    Button(
        modifier = modifier
            .width(200.dp)
            .aspectRatio(7/9f)
            .padding(horizontal = 7.dp)
            .clickable {
                //TODO : onClick
            },
        shape = RoundedCornerShape(5),
        border = BorderStroke(width = 0.5.dp, color = AlmostDarkGray),
        onClick = {onNavigateToMovieScreen(movieId)},
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
                    contentDescription = imageContentDescription + "_background",
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
                        ),
                )
                Box(modifier = modifier.fillMaxSize()) {
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
                                .aspectRatio(7 / 8.5f),
                            shape = RoundedCornerShape(5)
                        ) {
                            Image(
                                painter =
                                rememberImagePainter(
                                    data = imageUrl
                                ),
                                contentDescription = imageContentDescription,
                                contentScale = ContentScale.FillBounds,
                                modifier = modifier.fillMaxSize()
                            )
                        }
                        firstLine?.let {
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
                        secondLineForImdbRate?.let {
                            if (it != "") {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(it, color = AlmostYellow)
                                    Text(" of 10", color = Color.Gray)
                                }
                            }
                        }
                        secondLine?.let {
                            Text(it,color = AlmostYellow)
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
fun TwoLineMovieCardPreview(){
    val similar = FakeData.SampleMovie.similars?.get(0)
    IMdbAppTheme() {
        if (similar != null) {
            TwoLineMovieCard(imageContentDescription = "movie_image_preview",
                imageUrl = similar.image,
                firstLine = similar.fullTitle,
                secondLineForImdbRate = similar.imDbRating,
                secondLine = null, movieId = "12",
                onNavigateToMovieScreen = {}
                    )
        }
    }
}
