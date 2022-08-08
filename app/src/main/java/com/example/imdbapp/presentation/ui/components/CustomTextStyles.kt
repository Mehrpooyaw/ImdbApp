package com.example.imdbapp.presentation.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.imdbapp.presentation.theme.AlmostSilver
import com.example.imdbapp.presentation.theme.NeonGreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomTitleText(text : String,modifier : Modifier = Modifier,tint : Color = MaterialTheme.colors.secondary) {
    Text(
        text.uppercase(),
        color = tint,
        fontWeight = FontWeight.Light,
        modifier = modifier,


    )
}