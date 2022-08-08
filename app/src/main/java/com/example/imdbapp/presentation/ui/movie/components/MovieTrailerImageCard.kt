package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.imdbapp.presentation.ui.util.getRatioFromImageLink

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieTrailerThumbsCard(
    modifier : Modifier = Modifier,
    onClick : (String) -> Unit,
    imageUrl : String?,
    imdbTrailer : String,
){
    Button(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth(0.9f)
            .aspectRatio(1.5f)
            ,
        onClick = {
                  onClick(imdbTrailer)
        },
        shape = RoundedCornerShape(15),
        border = BorderStroke(1.dp,Color.Gray),
        contentPadding = PaddingValues(0.dp),
        ) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = rememberImagePainter(data = imageUrl),
                contentDescription = "movie_trailer_image",
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Box(modifier = modifier.clip(CircleShape).background(Color.DarkGray.copy(0.7f))) {
                Icon(imageVector = Icons.Rounded.PlayArrow, contentDescription = "",modifier = modifier.padding(5.dp).size(44.dp), tint = Color.White)
            }
        }
        
    }

    
}