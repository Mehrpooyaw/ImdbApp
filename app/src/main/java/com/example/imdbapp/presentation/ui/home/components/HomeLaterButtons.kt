package com.example.imdbapp.presentation.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.imdbapp.presentation.theme.*


enum class LaterButtons (val title : String){
    ComingSoon("Coming soon"),
    OnTheaters("In theaters"),
    BoxOffice("Box offices")
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeLaterButtons(
    modifier : Modifier = Modifier,
    onButtonClicked : (LaterButtons) -> Unit
) {
    Column() {
        LaterButtons.values().forEach {
            HomeLaterButtonsItem(laterButton = it,onClick = onButtonClicked)
        }

    }


}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeLaterButtonsItem(
    modifier : Modifier = Modifier,
    laterButton: LaterButtons,
    onClick : (LaterButtons) -> Unit
) {
    val color = if (MaterialTheme.colors.isLight) RedSunsetGradient else listOf(MaterialTheme.colors.secondary,MaterialTheme.colors.secondary,MaterialTheme.colors.secondary)
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = {
                  onClick(laterButton)
  },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 3.dp
        ),
        shape = RoundedCornerShape(25),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
                backgroundColor = if (MaterialTheme.colors.isLight) Color.White else MaterialTheme.colors.background,
                contentColor =  if (MaterialTheme.colors.isLight)  Color(221, 82, 77, 255) else Color(0xFFF890F8),
        ),
        border = BorderStroke(
            1.dp, Brush.horizontalGradient(
                listOf(
                    Color.Gray.copy(0.11f),
                    Color.Gray.copy(0.09f),
                    Color.Transparent,
                    Color.Transparent,
                ),


            )
        )
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        if (MaterialTheme.colors.isLight)
                            listOf(
                                Color.LightGray.copy(0.11f),
                                Color.LightGray.copy(0.09f),
                                Color.LightGray.copy(0.05f),
                                Color(233, 195, 100, 255).copy(alpha = 0.4f),
                                Color(221, 82, 77, 255).copy(alpha = 0.4f),
                            ) else
                            listOf(
                                Color.Gray.copy(0.11f),
                                Color.Gray.copy(0.09f),
                                Color.Gray.copy(0.05f),
                                Color.Magenta.copy(alpha = 0.1f),
                                Color.Magenta.copy(alpha = 0.3f),
                            ),
                        tileMode = TileMode.Mirror
                    )
                )
                .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(laterButton.title, fontFamily = FontFamily.SansSerif)
            Icon(Icons.Rounded.KeyboardArrowRight, "", tint = if (MaterialTheme
                    .colors.isLight)  Color(224, 73, 67, 255) else Color(0xFFF890F8))
        }
    }

}

