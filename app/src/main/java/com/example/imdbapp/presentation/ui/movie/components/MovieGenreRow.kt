package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.network.model.movie.Genre
import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.util.FakeData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieCRandGenresRow(modifier : Modifier = Modifier, genres : List<Genre>?, contentRating : String?){
    Row(modifier.fillMaxWidth().padding(vertical = 5.dp).padding(bottom = 5.dp),Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
        contentRating?.let { contentR ->
            if (contentR != "null") {
                Card(
                    border = BorderStroke(1.dp, brush = Brush.horizontalGradient(
                        if (MaterialTheme.colors.isLight)
                            listOf(
                                Purple700,
                                Purple700,
                            )
                        else
                            listOf(
                                MaterialTheme.colors.onBackground,
                                MaterialTheme.colors.onBackground,
                                Color(0xFFFFA500)
                            )
                    )),
                    shape = RoundedCornerShape(10.dp),
                    modifier = modifier.padding(start = 6.dp,end = 6.dp),
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    if (MaterialTheme.colors.isLight)
                                        Purple700
                                    else
                                        MaterialTheme.colors.onBackground

                                )
                            ) {
                                append("content rating : ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    if (MaterialTheme.colors.isLight)
                                        Purple700
                                    else
                                        Color(0xFFFFA500)

                                )
                            ) {
                                append(contentR)
                            }
                        },

                        modifier = modifier.padding(vertical = 7.dp, horizontal = 10.dp)
                    )
                }
            }
        }
        Box(){
            genres?.let {
                LazyRow(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {


                    items(genres) { genre ->
                        genre.key?.let { key ->
                            if (key != "") {
                                Card(
                                    border = BorderStroke(1.dp, color = NeonGreen),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = modifier.padding(end = 6.dp),
                                    backgroundColor = MaterialTheme.colors.background
                                ) {

                                    Text(
                                        key,
                                        color = NeonGreen,
                                        modifier = modifier.padding(
                                            vertical = 7.dp,
                                            horizontal = 7.dp
                                        )
                                    )
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
fun MovieGenresRowPreview(){
    val sampleMovie = FakeData.SampleMovie

        IMdbAppTheme() {

            MovieCRandGenresRow(genres = sampleMovie.genreList, contentRating = sampleMovie.contentRating)
        }

}