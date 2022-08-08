package com.example.imdbapp.presentation.ui.categories.components

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
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.imdbapp.domain.model.advanced_search.TopMovieType
import com.example.imdbapp.presentation.ui.components.CustomTitleText

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryBestOfMenu(
    modifier : Modifier = Modifier,
    onTopMovieClicked : (TopMovieType) -> Unit
){
    Column(
        modifier = modifier.fillMaxWidth().padding(horizontal = 5.dp)
    ) {
        CustomTitleText(text = "Top Movies Category ", modifier = modifier.padding(horizontal = 10.dp, vertical = 10.dp) )
        Row(modifier = modifier
            .fillMaxWidth()) {
            Box(
                modifier = modifier
                    .fillMaxWidth(0.5f)
                    .padding(5.dp)
            ) {
                CategoryBestOfMenuItem(
                    modifier = modifier,
                    title = "Top 250 movies",
                    tintGradient = listOf(Color(0xFFd53369),Color(0xFFdaae51)),
                      onClick = {
                          onTopMovieClicked(TopMovieType.Top250Movies)
                    }
                )
            }
            Box(
                modifier = modifier
                    .fillMaxWidth(1f)
                    .padding(5.dp)
            ) {
                CategoryBestOfMenuItem(
                    modifier = modifier,
                    title = "Best Picture winners",
                    tintGradient = listOf(
                        Color(0xFF0700b8), Color(0xFF00ff88),

                    ),
                    onClick = {
                        onTopMovieClicked(TopMovieType.BestPic)
                    }
                )
            }

        }
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth(0.5f)
                    .padding(5.dp)
            ) {
                CategoryBestOfMenuItem(
                    modifier = modifier,
                    title = "Golden Globe Winners",
                    tintGradient = listOf(
                        Color(0xff134E5E),
                        Color(0xff71B280),
                    ),
                    onClick = {
                        onTopMovieClicked(TopMovieType.GoldenGlobe)
                    }
                )
            }
            Box(
                modifier = modifier
                    .fillMaxWidth(1f)
                    .padding(5.dp)
            ) {
                CategoryBestOfMenuItem(
                    modifier = modifier,
                    title = "Oscar Winners",
                    tintGradient = listOf(
                        Color(0xFFDB3232),
                        Color(0xFF545FA2)
                    ),
                    onClick = {
                        onTopMovieClicked(TopMovieType.Oscar)
                    }
                )
            }


        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryBestOfMenuItem(modifier: Modifier,
                           title: String,
                           tintGradient: List<Color>,
                           textColor : Color = Color.White,
                           roundCorner : Int = 7,
                           textWeight : FontWeight = FontWeight.W500,
                           textSize : TextUnit = TextUnit.Unspecified,
                           onClick: () -> Unit) {

    Button(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 10.dp,
            pressedElevation = 0.dp
        ),
        shape = RoundedCornerShape(roundCorner),
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background
        )
        
    ){
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        tintGradient,
                        tileMode = TileMode.Mirror,


                        )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                modifier = modifier.align(Alignment.Center),
                fontFamily = FontFamily.SansSerif,
                fontWeight = textWeight,
                color = textColor,
                fontSize = textSize,
                )
        }
        
    }
        
}

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryBestOfMenuPreview(
){
    Scaffold {
        CategoryBestOfMenu(
            onTopMovieClicked = {}
        )
    }

}