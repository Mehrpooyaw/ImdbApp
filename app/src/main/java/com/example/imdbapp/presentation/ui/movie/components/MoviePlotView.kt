package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.presentation.theme.NeonBlue
import com.example.imdbapp.presentation.theme.NeonGreen
import com.example.imdbapp.util.FakeData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviePlot(plot : String,modifier : Modifier = Modifier){
    val isExpanded = remember { mutableStateOf(false) }

    Column(
        modifier
            .fillMaxWidth()
            .padding(5.dp)
            .animateContentSize()
            .padding(1.dp)
    ) {
            if (isExpanded.value){
                Text(
                    plot,
                    modifier = modifier.padding(horizontal = 5.dp),
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.body1

                )
            }else{
                Text(
                    plot,
                    modifier = modifier.padding(horizontal = 5.dp),
                    textAlign = TextAlign.Justify,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1

                )
            }
            Box(
                modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp), Alignment.Center
            ) {
                Button(
                    onClick = { isExpanded.value = !isExpanded.value },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = Color.White
                    ),
                    modifier = modifier.height(26.dp),
                    contentPadding = PaddingValues(0.dp)

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.fillMaxHeight().background(
                            Brush.linearGradient(listOf(NeonBlue, NeonGreen))
                        ).padding(horizontal = 10.dp )
                    ) {
                        Text(if (isExpanded.value) "Less" else "More",)

                        Icon(
                            if (isExpanded.value) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "",
                            modifier = modifier.size(20.dp)
                        )
                    }
                }
            }

    }
}


@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviePlotPreview(){
    FakeData.SampleMovie.plot?.let { MoviePlot(plot = it) }
}
