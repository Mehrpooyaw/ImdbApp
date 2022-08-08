package com.example.imdbapp.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.theme.NeonGreen


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BoxWithTitleAndMoreButton(
    modifier : Modifier = Modifier,
    onMoreClick : () -> Unit,
    title : String,
    subTitle : String,
    tint : Color = NeonGreen,
    isMoreAvailable : Boolean = true,
    content:
@Composable () -> Unit
){
    Column(modifier.fillMaxWidth()) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(5.dp),Arrangement.SpaceBetween,Alignment.Bottom){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = modifier
                    .padding(end = 10.dp)
                    .clip(RoundedCornerShape(50))
                    .size(width = 7.dp, height = 35.dp)
                    .background(tint))
                Column() {
                    Text(
                        text = title,
                        color = MaterialTheme.colors.onBackground,
                        fontFamily = FontFamily.SansSerif,
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    Text(
                        text = subTitle.uppercase(),
                        color = tint,
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            }
            if (isMoreAvailable) {
                Text(
                    text = "See more",
                    color = MaterialTheme.colors.secondaryVariant,
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline,
                    modifier = modifier
                        .padding(end = 5.dp)
                        .clickable {
                            onMoreClick()
                        }
                )
            }

        }
        Box(modifier.fillMaxWidth()){
            content()
        }
    }

}

