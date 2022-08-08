package com.example.imdbapp.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = NeonPink,
    primaryVariant = Purple700,
    secondary = NeonGreen,

    background = AlmostBlack,
    surface = AlmostDarkGray,
    secondaryVariant = NeonBlue,
    //    onPrimary = Color.White,
    //    onSecondary = Color.Black,
        onBackground = AlmostSilver,
    //    onSurface = Color.Black,
)
private val LightColorPalette = lightColors(
    primary = DarkNeonPink,
    primaryVariant = Purple700,
    secondary = DarkNeonGreen,

    background = Cream,
    surface = GrayAndBlue,
    error = Color(0xFFB00020),
    onPrimary = Color.White,
    onSecondary= Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White,
    secondaryVariant = DarkNeonBlue

)



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IMdbAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content:
@Composable() () -> Unit) {

    val colors = if (darkTheme) DarkColorPalette else LightColorPalette




    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}