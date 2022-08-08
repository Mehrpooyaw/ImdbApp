package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imdbapp.R
import com.example.imdbapp.presentation.theme.BabyBlueGradient
import com.example.imdbapp.presentation.theme.MauvelousGradient
import com.example.imdbapp.presentation.theme.TGreenGradient
import com.example.imdbapp.presentation.theme.VodkaGradient
import com.example.imdbapp.presentation.ui.components.BackButton
import com.example.imdbapp.presentation.ui.components.IconAndVerticalKeyValueWithRoundedShape
import com.example.imdbapp.presentation.ui.util.TOP_APP_PADDING
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieLoadingView(
    modifier : Modifier = Modifier,
    onBackPressed : () -> Unit
){
    
    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(top = TOP_APP_PADDING), horizontalAlignment = Alignment.CenterHorizontally) {
        BackButton(onBackPressedClick = onBackPressed, modifier = modifier.align(Start))
        Card(
            modifier = modifier
                .padding(horizontal = 15.dp, vertical = 10.dp)
                .fillMaxWidth()
                .aspectRatio(7 / 9.1f)
                .placeholder(
                    visible = true,
                    color = Color.LightGray,
                    highlight = PlaceholderHighlight.shimmer(),
                    shape = RoundedCornerShape(5)

                ),
            shape = RoundedCornerShape(10)
        ){
            
        }
        Text(

            text = "some thing this big is enough",
            modifier = modifier
                .padding(vertical = 5.dp, horizontal = 5.dp)
                .placeholder(
                    visible = true,
                    shape = RoundedCornerShape(30),
                    highlight = PlaceholderHighlight.fade(),
                    color = Color.LightGray
                ),
            fontFamily = FontFamily.Monospace,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.primary
        )

        Row(modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .height(95.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.fillMaxHeight()
            ) {
                IconAndVerticalKeyValueWithRoundedShape(
                    key = "type",
                    value = "type" ?: "Not found",
                    iconId = R.drawable.icon_projector,
                    tintGradient = BabyBlueGradient,
                    modifier = modifier.placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(),
                        color = BabyBlueGradient[0]
                    )
                )
                Spacer(modifier.height(3.dp))
                IconAndVerticalKeyValueWithRoundedShape(
                    key = "release date",
                    value = "releaseDate" ?: "Not found",
                    iconId = R.drawable.ic_round_date_range_24,
                    tintGradient = MauvelousGradient,
                    modifier = modifier.placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(),
                        color = MauvelousGradient[0]
                    )
                )
                

            }
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.fillMaxHeight()
            ) {
                IconAndVerticalKeyValueWithRoundedShape(
                    key = "languages",
                    value = "languages" ?: "Not found",
                    iconId = R.drawable.ic_round_language_24,
                    tintGradient = VodkaGradient,
                    modifier = modifier.placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(),
                        color = VodkaGradient[0]
                    )
                )
                Spacer(modifier.height(3.dp))

                IconAndVerticalKeyValueWithRoundedShape(
                    key = "runtime",
                    value = "runTime" ?: "Not found",
                    iconId = R.drawable.icon_timer,
                    tintGradient = TGreenGradient,
                    modifier = modifier.placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(),
                        color = TGreenGradient[0]
                    )
                )

            }
        }
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
        Text(modifier = modifier
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .fillMaxWidth()
            .placeholder(
                visible = true,
                shape = RoundedCornerShape(30),
                highlight = PlaceholderHighlight.fade(),
                color = Color.LightGray
            ), text = "")
        Text(modifier = modifier
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .fillMaxWidth()
            .placeholder(
                visible = true,
                shape = RoundedCornerShape(30),
                highlight = PlaceholderHighlight.fade(),
                color = Color.LightGray
            ), text = "")
        Text(modifier = modifier
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .fillMaxWidth()
            .placeholder(
                visible = true,
                shape = RoundedCornerShape(30),
                highlight = PlaceholderHighlight.fade(),
                color = Color.LightGray
            ), text = "")
    }
    
}

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieLoadingViewPreview(){
    MovieLoadingView() {
        
    }
}
