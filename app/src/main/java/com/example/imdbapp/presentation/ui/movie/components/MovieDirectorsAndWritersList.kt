package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.R
import com.example.imdbapp.domain.model.Movie
import com.example.imdbapp.presentation.theme.NeonBlue
import com.example.imdbapp.presentation.ui.components.CustomTitleText
import com.example.imdbapp.util.FakeData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDirectorsAndWritersRowList(
    modifier : Modifier = Modifier,
    movie : Movie,
    onClick : (String) -> Unit
    ){
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(5.dp)
        .background(Color.Transparent)){
        Box(
            modifier = modifier
                .fillMaxWidth(1 / 2f)
                .padding(end = 5.dp),


        ){
            Column(modifier = modifier
                .fillMaxWidth()
                .padding(5.dp)
            ) {
                CustomTitleText(text = "Directors")
                Spacer(modifier = modifier.height(5.dp))
                for (i in movie.directorList!!) {
                    Button(
                        shape = RoundedCornerShape(25),
                        modifier = modifier.padding(vertical = 3.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = NeonBlue,
                            contentColor = Color.White
                        ),
                        onClick = {
                            i.id?.let { onClick(it) }
                        },
                        enabled = i.id != null,
                        contentPadding = PaddingValues(0.dp)

                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.padding(vertical = 5.dp, horizontal = 5.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_round_person_24),
                                contentDescription = "",

                            )
                            Text(
                                text = i.name ?: "", modifier.padding(horizontal = 3.dp),

                                )
                        }
                    }
                }
            }

        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 5.dp),
        ){
            Column(modifier = modifier
                .fillMaxWidth()
                .padding(5.dp)
            ) {
                CustomTitleText(text = "Writers")
                Spacer(modifier = modifier.height(5.dp))

                for (i in movie.writerList!!){
                    Button(
                        shape = RoundedCornerShape(25),
                        modifier = modifier.padding(vertical = 3.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = NeonBlue,
                            contentColor = Color.White
                        ),
                        onClick = {
                            i.id?.let { onClick(it) }
                        },
                        enabled = i.id != null,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.padding(vertical = 5.dp, horizontal = 5.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_round_person_24),
                                contentDescription = "",

                            )
                            Text(
                                text = i.name ?: "", modifier.padding(horizontal = 3.dp),

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
fun MovieDirectorsAndWritersRowListPreview(){
    val sampleMovie = FakeData.SampleMovie
    MovieDirectorsAndWritersRowList(movie = sampleMovie){}
}