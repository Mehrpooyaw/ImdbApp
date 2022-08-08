package com.example.imdbapp.presentation.ui.person.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.imdbapp.presentation.theme.*
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PersonLoadingView(
    modifier: Modifier = Modifier,
    onBackPressed : () -> Unit
){
    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors =
                        if (MaterialTheme.colors.isLight)
                            listOf(
                                Color.Transparent, MaterialTheme.colors.background
                            )
                        else
                            listOf(
                                Color.Black, Color.Black, Color.Black, AlmostBlack
                            )
                    )

                )
                .padding(top = 26.dp)
        ) {
            Column(
                modifier = modifier
                    .padding(horizontal = 10.dp)
                    .height(250.dp),
                verticalArrangement = Arrangement.Top
            ) {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        "",
                        modifier = modifier.size(30.dp),
                        tint =
                        if (MaterialTheme.colors.isLight)
                            Color.DarkGray
                        else
                            AlmostSilver
                    )
                }
            }
            Card(
                elevation = 20.dp,
                shape = RoundedCornerShape(bottomStartPercent = 15, topStartPercent = 8),
                modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(0.7f)
                    .placeholder(
                        visible = true,
                        shape = RoundedCornerShape(bottomStartPercent = 15, topStartPercent = 8),
                        color = Color.LightGray,
                        highlight = PlaceholderHighlight.shimmer()
                    ),
                border = BorderStroke(
                    1.dp, Brush.linearGradient(
                        colors = if (MaterialTheme.colors.isLight)
                            GlassyGradient
                        else
                            AlmostBlackToDarkGrayGradient
                    )
                )
            ) {

            }

        }
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(
                modifier = modifier
                    .padding(horizontal = 10.dp)
                    .size(width = 5.dp, height = 50.dp)
                    .clip(
                        RoundedCornerShape(50)
                    )
                    .background(
                        Brush.verticalGradient(
                            tileMode = TileMode.Clamp,
                            colors = listOf(NeonPink, NeonGreen)
                        )
                    )
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(30),
                        highlight = PlaceholderHighlight.fade()
                    )
            )
            Column(
                modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = "s",
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(30),
                            highlight = PlaceholderHighlight.fade()
                        ),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NeonGreen
                )
                    Text(
                        text = "s",
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .placeholder(
                                visible = true,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(30),
                                highlight = PlaceholderHighlight.fade()
                            ),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = NeonGreen
                    )

            }
        }
        repeat(4){
            Text(modifier = modifier
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .padding(top = 5.dp)
                .fillMaxWidth()
                .placeholder(
                    visible = true,
                    shape = RoundedCornerShape(30),
                    highlight = PlaceholderHighlight.fade(),
                    color = Color.LightGray
                ), text = "")
        }
    }


}