package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.R
import com.example.imdbapp.domain.model.Reviews
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.util.FakeData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieReviewsButton(
    modifier : Modifier = Modifier,
    onClick : (String) -> Unit,
    id : String
){
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {


        Button(
            modifier = modifier

                .padding(horizontal = 30.dp)
                .fillMaxWidth(0.85f)
                .padding(vertical = 20.dp)
                .height(80.dp),
            onClick = {
                onClick(id)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background,
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(

                            if (MaterialTheme.colors.isLight)
                                listOf(
                                    MaterialTheme.colors.primary.copy(alpha = 0.9f),
                                    MaterialTheme.colors.primary.copy(alpha = 0.6f),
                                    MaterialTheme.colors.primary.copy(alpha = 0.4f),
                                    MaterialTheme.colors.primary.copy(alpha = 0.6f),
                                    MaterialTheme.colors.primary.copy(alpha = 0.9f),
                                )
                            else
                                listOf(
                                    MaterialTheme.colors.primary.copy(alpha = 0.6f),
                                    MaterialTheme.colors.primary.copy(alpha = 0.5f),
                                    MaterialTheme.colors.primary.copy(alpha = 0.4f),
                                    MaterialTheme.colors.primary.copy(alpha = 0.3f),
                                    MaterialTheme.colors.primary.copy(alpha = 0.2f),

                                ),
                            tileMode = TileMode.Decal
                        )
                    ), contentAlignment = Alignment.Center
            ) {
                Text("Movie Reviews", style = MaterialTheme.typography.button, color = Color.White)
            }
        }
    }

}




@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieReviewButtonPreview(){
    IMdbAppTheme (darkTheme = true){
        Scaffold() {


            MovieReviewsButton(id = "1", onClick = {})
        }
    }
}