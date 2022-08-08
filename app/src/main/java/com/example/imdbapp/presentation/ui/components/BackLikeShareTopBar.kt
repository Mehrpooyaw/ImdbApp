package com.example.imdbapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.domain.model.Movie
import com.example.imdbapp.presentation.theme.IMdbAppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BackLikeShareTopBar(
    modifier : Modifier = Modifier,
    movie : Movie,
    isFavorite : Boolean,
    onBackPressed: () -> Unit,
    onFavoriteButtonClicked : (Movie) -> Unit
     ){

    Row(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 10.dp)
        .height(42.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Box(modifier =
        modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colors.onBackground.copy(alpha = 0.1f))
            .clickable {
                onBackPressed()

            },
            contentAlignment = Alignment.Center,
        ) {
            Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "",
                modifier = modifier.size(28.dp), tint = Color.White)
        }
        Row(verticalAlignment = Alignment.CenterVertically){
            Box(modifier =
            modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.onBackground.copy(alpha = 0.1f))
                .clickable {
                    onFavoriteButtonClicked(movie)
                    //TODO : OnClick
                },
                contentAlignment = Alignment.Center,
            ) {
                Icon(imageVector = Icons.Rounded.Star, contentDescription = "",
                    modifier = modifier.size(28.dp), tint = if (isFavorite) Color.Yellow else Color.White)
            }
            Spacer(modifier.width(10.dp))

            Box(modifier =
            modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.onBackground.copy(alpha = 0.1f))
                .clickable {
                    //TODO : OnClick

                },
                contentAlignment = Alignment.Center,
            ) {
                Icon(imageVector = Icons.Rounded.Share, contentDescription = "",
                    modifier = modifier.size(28.dp), tint = Color.White)
            }
        }

    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    tint : Color =  MaterialTheme.colors.onBackground,
            onBackPressedClick : () -> Unit,

){
    IconButton(
        onClick = onBackPressedClick,
        modifier = modifier.size(40.dp),){
        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "",modifier = Modifier.size(24.dp), tint = tint)
    }

}

