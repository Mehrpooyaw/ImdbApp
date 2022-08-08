package com.example.imdbapp.presentation.ui.explore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.presentation.theme.AlmostDarkGray
import com.example.imdbapp.presentation.theme.DarkNeonGreen
import com.example.imdbapp.presentation.ui.components.CustomTitleText
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExploreLoadingView(
    modifier : Modifier = Modifier
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        Box(modifier = modifier
            .fillMaxWidth()


            .aspectRatio(1.03f)){
            Spacer(modifier = modifier.fillMaxSize().placeholder(
                visible = true,
                color = Color.LightGray,
                highlight = PlaceholderHighlight.shimmer(),
            ))
            Spacer(modifier = modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            MaterialTheme.colors.background
                        )
                    )
                ))
        }




        Box(
            modifier = modifier.padding(horizontal = 90.dp)
        ) {


            Box(
                modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Card(
                    modifier = modifier
                        .align(Alignment.BottomCenter)
                        .graphicsLayer {
                            rotationX = 60f


                        }
                        .fillMaxWidth(0.8f)
                        .aspectRatio(1.1f)
                        .placeholder(
                            visible = true,
                            color = DarkNeonGreen.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(25),
                            highlight = PlaceholderHighlight.fade()
                        ),
                    shape = RoundedCornerShape(25),
                    backgroundColor = DarkNeonGreen.copy(alpha = 0.5f)
                ) {
                }
                Button(
                    modifier = modifier
                        .padding(top = 30.dp)
                        .align(Alignment.TopCenter)
                        .fillMaxHeight(0.7f)
                        .aspectRatio(0.77f)
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(7),
                            highlight = PlaceholderHighlight.fade()
                        ),
                    shape = RoundedCornerShape(7),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = AlmostDarkGray
                    ),
                    contentPadding = PaddingValues(0.dp),
                    onClick = {

                    },

                    ) {

                }

            }

        }
        CustomTitleText(
            text = "COMING SOON MOVIES",
            modifier = modifier
                .align(CenterHorizontally)
                .padding(top = 25.dp)
                .placeholder(
                    visible = true,
                    shape = RoundedCornerShape(20),
                    color = Color.LightGray,
                    highlight = PlaceholderHighlight.fade()
                )
        )
        Divider(
            modifier = modifier
                .padding(vertical = 15.dp)
                .fillMaxWidth()
                .placeholder(
                    visible = true,
                    color = Color.LightGray,
                    highlight = PlaceholderHighlight.fade()
                )
        )
        Row(
            modifier = modifier.fillMaxWidth()
        ){
            repeat(3){
                Box(modifier = modifier
                    .weight(1f)
                    .aspectRatio(0.67f)
                    .padding(5.dp)
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(12),
                        highlight = PlaceholderHighlight.fade()
                    ))
            }
        }

    }
}
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExploreLoadingPreview(){
    Scaffold() {


        ExploreLoadingView()
    }
}
