package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.foundation.layout.*
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.R
import com.example.imdbapp.presentation.theme.BabyBlueGradient
import com.example.imdbapp.presentation.theme.MauvelousGradient
import com.example.imdbapp.presentation.theme.TGreenGradient
import com.example.imdbapp.presentation.theme.VodkaGradient
import com.example.imdbapp.presentation.ui.components.IconAndVerticalKeyValueWithRoundedShape
import com.example.imdbapp.util.FakeData


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BaseDetailAndRatingBox(
    modifier : Modifier = Modifier,
    runTime : String?,
    releaseDate : String?,
    type : String?,
    languages : String?){
    Row(modifier = modifier
        .fillMaxWidth().padding(horizontal = 5.dp)
        .height(95.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically)
    {

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxHeight()
        ) {
            IconAndVerticalKeyValueWithRoundedShape(
                key = "type",
                value = type ?: "Not found",
                iconId = R.drawable.icon_projector,
                tintGradient = BabyBlueGradient
            )
            Spacer(modifier.height(3.dp))
            IconAndVerticalKeyValueWithRoundedShape(
                key = "release date",
                value = releaseDate ?: "Not found",
                iconId = R.drawable.ic_round_date_range_24,
                tintGradient = MauvelousGradient
            )

        }
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxHeight()
        ) {
            IconAndVerticalKeyValueWithRoundedShape(
                key = "languages",
                value = languages ?: "Not found",
                iconId = R.drawable.ic_round_language_24,
                tintGradient = VodkaGradient
            )
            Spacer(modifier.height(3.dp))

            IconAndVerticalKeyValueWithRoundedShape(
                key = "runtime",
                value = runTime ?: "Not found",
                iconId = R.drawable.icon_timer,
                tintGradient = TGreenGradient
            )

        }




    }

}



@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BaseDetailAndRatingBoxPreview(){
    val movie = FakeData.SampleMovie
    BaseDetailAndRatingBox(runTime = movie.runtimeStr , releaseDate =movie.releaseDate ,  languages = movie.languages, type = movie.type)
}