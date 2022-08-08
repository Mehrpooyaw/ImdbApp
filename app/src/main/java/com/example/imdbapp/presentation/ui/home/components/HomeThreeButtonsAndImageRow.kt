package com.example.imdbapp.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.imdbapp.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeThreeButtonsAnImageRow(
    modifier : Modifier = Modifier,
    onButtonClicked : (LaterButtons) -> Unit
    ){
    Column(
        modifier
            .fillMaxSize()
            .padding(vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = painterResource(id = R.drawable.icon_home_plastilina),
            contentDescription = "",
            modifier = modifier
                .padding(5.dp)
                .fillMaxWidth(0.8f),
            alpha = 0.8f

        )

        Box(
            modifier
                .fillMaxWidth(1f)
        ) {
            HomeLaterButtons(
                onButtonClicked = onButtonClicked)
        }
    }
}