package com.example.imdbapp.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.domain.model.advanced_search.TopMovieType
import com.example.imdbapp.presentation.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeSquareButtons(
    modifier : Modifier = Modifier,
    onNavigateToTopMovies : (TopMovieType) -> Unit,

){
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Box(
                    modifier = modifier
                        .weight(0.6f)
                        .fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    HomeSquareButtonCard(
                        onClick = {
                            onNavigateToTopMovies(TopMovieType.GoldenGlobe)
                        },
                        text = "Golden Globe\nwinners",
                        gradient = listOf(
                            Color(90, 8, 46, 255),
                            Color(255, 141, 0, 255),
                        )
                    )
                }
                Box(
                    modifier = modifier
                        .weight(0.4f)
                        .fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    HomeSquareButtonCard(
                        onClick = {
                            onNavigateToTopMovies(TopMovieType.BestDirector)
                        },
                        text = "director\nwinners",
                        gradient = TitaniumGradient

                    )

                }
            }
            Column(
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Box(
                    modifier = modifier
                        .weight(0.4f)
                        .fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    HomeSquareButtonCard(
                        onClick = {
                            onNavigateToTopMovies(TopMovieType.Emmy)
                        },
                        text = "Emmy Award\nwinners",
                        gradient = AlmostRedGradient

                    )
                }
                Box(
                    modifier = modifier
                        .weight(0.6f)
                        .fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    HomeSquareButtonCard(
                        onClick = {
                            onNavigateToTopMovies(TopMovieType.Oscar)
                        },
                        text = "Oscar\nwinners",
                        gradient = UnderTheLakeGradient

                    )

                }
            }
        }
    }

}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeSquareButtonCard(
    modifier : Modifier = Modifier,
    onClick : () -> Unit,
    text : String,
    gradient : List<Color>
){
    Button(
        modifier = modifier
            .padding(5.dp)
            .fillMaxSize(1f),
        onClick = onClick,
        shape = RoundedCornerShape(15),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.elevation(
            pressedElevation = 40.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = if(MaterialTheme.colors.isLight) AlmostSilver else MaterialTheme.colors.secondary
        )
        ){
        Box(modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        gradient[0].copy(alpha = 0.8f),
                        gradient[0].copy(alpha = 0.6f),
                        gradient[0].copy(alpha = 0.5f),
                        gradient[0].copy(alpha = 0.6f),
                        gradient[0].copy(alpha = 0.9f),


                        )
                )
            ), contentAlignment = Alignment.Center){
            Text(text = text,
                style = MaterialTheme.typography.caption, textAlign = TextAlign.Center
                )
        }
    }
}

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeSquareButtonsPreview() {

    IMdbAppTheme() {


        Scaffold() {


            HomeSquareButtons(
                onNavigateToTopMovies = {})
        }
    }

}

