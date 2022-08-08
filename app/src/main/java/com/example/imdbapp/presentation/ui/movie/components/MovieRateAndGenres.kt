package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.R
import com.example.imdbapp.interactors.movie.util.convertIntRateToCountAndDecimal
import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.util.FakeData


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieImdbRateRow(modifier: Modifier = Modifier, imDbRate: String, imdbVotes: String?){
    Row(modifier = modifier
        .fillMaxWidth()
        .background(
            Brush.linearGradient(
            if (MaterialTheme.colors.isLight)
                listOf(MaterialTheme.colors.primary.copy(0.5f),
                MaterialTheme.colors.primary.copy(0.7f),
)
            else
                listOf( Color.Transparent,Color.Transparent)
            ,
        ))
        .padding(10.dp,), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
        Row(verticalAlignment = Alignment.Bottom) {
                ImdbRateStars(imDbRate = imDbRate.toDouble())

            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = AlmostYellow, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold
                        ),
                    ) {
                        append(" $imDbRate")
                    }
                    withStyle(style = SpanStyle(color = if (MaterialTheme.colors.isLight)
                        Color.LightGray
                    else
                        MaterialTheme.colors.onBackground
                        , fontFamily = FontFamily.Serif)){
                        append(" / 10")

                    }

                },modifier = modifier.padding(horizontal = 3.dp)
                )
        }
        if (imdbVotes != null) {
            Text(
                buildAnnotatedString {

                    withStyle(style = SpanStyle(
                        if (MaterialTheme.colors.isLight)
                        Color.White
                    else
                        AlmostYellow
                        ,)){
                        append("  ${"%,d".format(imdbVotes.toInt())}  ")
                    }
                    withStyle(style = SpanStyle(color =if (MaterialTheme.colors.isLight)
                        Color.LightGray
                    else
                        MaterialTheme.colors.onBackground
                        , fontFamily = FontFamily.Serif)){
                        append("votes")
                    }

                }
            )
        }
    }



}



@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieRateAndGenres(){
    val sampleMovie = FakeData.SampleMovie
    IMdbAppTheme {
        Scaffold() {

        sampleMovie.imDbRating?.let {
            sampleMovie.imDbRatingVotes?.let { it1 ->
                    MovieImdbRateRow(
                        imDbRate = it,
                        imdbVotes = it1
                    )
                }

            }

        }
    }
}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ImdbRateStars(imDbRate: Double){
    val countAndDecimal = convertIntRateToCountAndDecimal(imDbRate)
    Row() {
        if (countAndDecimal.first == 5) {
            repeat(countAndDecimal.first) {
                Icon(imageVector = Icons.Rounded.Star, contentDescription = "", tint = AlmostYellow)
            }
        } else if (countAndDecimal.first == 0 && countAndDecimal.second == 0.0) {
            repeat(5) {
                Icon(imageVector = Icons.Rounded.Star, contentDescription = "", tint = Color.White)
            }
        } else {
            repeat(countAndDecimal.first) {
                Icon(imageVector = Icons.Rounded.Star, contentDescription = "", tint = AlmostYellow)
            }
            DecimalToStarIcon(decimal = countAndDecimal.second)
            repeat(4 - countAndDecimal.first) {
                Icon(imageVector = Icons.Rounded.Star, contentDescription = "", tint = Color.White)

            }
        }
    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DecimalToStarIcon(decimal : Double) {
    return if (decimal < 0.5){
        Icon(imageVector = Icons.Rounded.Star, contentDescription = "", tint  = Color.White)
    }
    else{ // 0.5 or higher.
        Box(){
            Icon(imageVector = Icons.Rounded.Star, contentDescription = "", tint = Color.White)
            Icon(painter = painterResource(id = R.drawable.ic_round_star_half_24), contentDescription = "", tint = AlmostYellow)
        }
    }

}

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ImdbRateStarsPreview(){
    val sampleRate = FakeData.SampleMovie.imDbRating
    sampleRate?.let {
        Card(
            shape = RoundedCornerShape(10),
            elevation = 10.dp,
            backgroundColor = AlmostBlack
        ) {

            Box(Modifier.padding(5.dp)) {


                ImdbRateStars(imDbRate = sampleRate.toDouble())
            }
        }
    }
}