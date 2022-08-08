package com.example.imdbapp.presentation.ui.explore.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.imdbapp.network.model.listItemMovies.Item
import com.example.imdbapp.network.model.search.Result
import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.presentation.ui.components.HorizontalKeyValue
import com.example.imdbapp.presentation.ui.util.ImageQuality
import com.example.imdbapp.presentation.ui.util.changeImageQuality
import com.example.imdbapp.presentation.ui.util.getRatioFromImageLink
import com.example.imdbapp.util.FakeData


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchMovieItemCard(
    modifier : Modifier = Modifier,
    searchResult : Result,
    onClick : (Result) -> Unit
) {
    val imagePainter = rememberImagePainter(data = changeImageQuality(
        url = searchResult.image,
    quality = ImageQuality.Medium.str))

    Button(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .aspectRatio(18 / 9f),
        onClick = {
            onClick(searchResult)
        },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 8.dp,
            pressedElevation = 12.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background
        ),
        shape = RoundedCornerShape(20),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(modifier.fillMaxSize()) {

            Row(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                                TitaniumGradient.reversed(),

                            tileMode = TileMode.Clamp
                        )
                    )
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = modifier
                        .padding(end = 10.dp)
                        .fillMaxHeight()
                        .aspectRatio(getRatioFromImageLink(searchResult.image)?: 0.7f),
                    shape = RoundedCornerShape(25),
                    elevation = 10.dp,
                ) {
                    Image(
                        modifier = modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds,
                        painter = imagePainter,
                        contentDescription = "home_21_9_card_movie"
                    )

                }
                Column(
                    modifier = modifier.fillMaxSize().padding(horizontal = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        searchResult.title?.let {
                            if (it != "") {
                                Text(
                                    text = it,

                                    fontFamily = FontFamily.SansSerif,
                                    color = Color.Cyan
                                )
                            }
                        }
                        searchResult.resultType?.let {
                            if (it != "") {
                                Text(it, color = AlmostYellow)
                            }
                        }
                    }

                    searchResult.description?.let {
                        if (it != "") {
                            Text(
                                text = it,
                                modifier = modifier.fillMaxWidth().align(Alignment.Start),
                                textAlign = TextAlign.Start,
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






@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieItemCardPreview(){
    val searchResult = FakeData.sampleSearchResult
    IMdbAppTheme() {
        Scaffold() {
            LazyColumn(modifier = Modifier.fillMaxSize().background(Brush.linearGradient(
                listOf(
                    Color(0xFFFF80AB),
                    Color(0xFFB9F6CA),
                )
            ))){
                items(searchResult.results!!) {
                    SearchMovieItemCard(searchResult = it){}
                }
            }
        }
    }
}