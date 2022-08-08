package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imdbapp.R
import com.example.imdbapp.domain.model.Movie
import com.example.imdbapp.domain.util.convertAwardsStrToList
import com.example.imdbapp.presentation.ui.components.CustomTitleText
import com.example.imdbapp.presentation.ui.components.HorizontalKeyValue
import com.example.imdbapp.util.FakeData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieAwardsView(modifier : Modifier = Modifier, movie : Movie){
    Column(modifier.padding(horizontal = 5.dp)) {
        CustomTitleText(text = "Awards and ratings",modifier = modifier.padding(start = 5.dp))
        Spacer(modifier = modifier.height(2.dp))

        if (movie.awards != null) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = modifier.size(70.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.trophy_icon),
                        contentDescription = "",
                        modifier = modifier.fillMaxSize()
                    )
                }
                Column(
                    modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    for (i in convertAwardsStrToList(movie.awards)) {
                        Text(
                            text = i,
                            lineHeight = 16.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Thin,
                            style = MaterialTheme.typography.body2,
                            modifier = modifier.padding(start = 1.dp, bottom = 5.dp)
                        )
                    }
                }
            }
        }
        Row(
            modifier = modifier.fillMaxWidth().padding(7.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(){
                if (movie.ratings != null) {
                    movie.ratings.metacritic?.let {
                        if (it != "") {
                            HorizontalKeyValue(key = "Metacritic", value = it)
                        }
                    }
                    movie.ratings.filmAffinity?.let {
                        if (it != "") {
                            HorizontalKeyValue(key = "Film Affinity", value = it)
                        }
                    }
                    movie.ratings.rottenTomatoes?.let {
                        if (it != ""){
                            HorizontalKeyValue(key = "Rotten Tomatoes", value = it)
                        }
                    }
                    movie.ratings.tV_com?.let {
                        if (it != "") {
                            HorizontalKeyValue(key = "tV_com", value = it)
                        }
                    }
                }
            }
        }
    }


}


@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AwardsViewPreview(){
    val movie = FakeData.SampleMovie
    MovieAwardsView(movie = movie)
}