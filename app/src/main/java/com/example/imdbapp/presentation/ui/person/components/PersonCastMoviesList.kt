package com.example.imdbapp.presentation.ui.person.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.network.model.person.CastMovy
import com.example.imdbapp.presentation.theme.AlmostSilver
import com.example.imdbapp.presentation.theme.AlmostYellow
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.theme.NeonPink
import com.example.imdbapp.presentation.ui.components.CustomTitleText
import com.example.imdbapp.util.FakeData


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PersonCastMoviesList(
    modifier : Modifier = Modifier,
    castMovies : List<CastMovy>
) {
    Column(modifier.padding(10.dp)) {
        Row(modifier.padding(horizontal = 10.dp).fillMaxWidth(),Arrangement.SpaceBetween) {
            CustomTitleText(
                text = "Cast movies",
                modifier = modifier.padding(bottom = 5.dp)
            )
            CustomTitleText(text = "${castMovies.size} items")
        }
        castMovies.forEach { castMovie ->
            PersonCastMoviesListItem(castMovie = castMovie)

        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PersonCastMoviesListItem(
    modifier : Modifier = Modifier,
    castMovie : CastMovy
){

    Card(modifier = modifier
        .padding(vertical = 10.dp, horizontal = 25.dp)
        .fillMaxWidth(), shape = RoundedCornerShape(6.dp)) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {

            Row(
                modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                Arrangement.SpaceBetween
            ) {
                castMovie.title?.let { it1 ->
                    if (it1 != "") {
                        Text(it1, modifier.fillMaxWidth(0.65f), color = NeonPink)
                    }
                    Row(modifier.fillMaxWidth(), Arrangement.SpaceBetween) {

                        castMovie.year?.let { it2 ->
                            if (it2 != "") {
                                Text(it2,color = AlmostYellow)
                            } else {
                                Text("    ")
                            }
                        }
                        castMovie.role?.let { it3 ->
                            if (it3 != "") {
                                Text(it3, color = Color.Cyan)
                            }
                        }
                    }
                }
            }
            Divider(modifier = modifier
                .padding(horizontal = 30.dp, vertical = 3.dp)
                .fillMaxWidth(), color = AlmostSilver)
            castMovie.description?.let {
                if (it != "") {
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Gray, fontWeight = FontWeight.SemiBold)) {
                            append("Description : ")
                        }
                        append(it)
                    })



                }
            }
        }
    }


}



@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PersonCastMoviesListPreview(){
    IMdbAppTheme() {
        Scaffold(){
            FakeData.SamplePerson.castMovies?.let { it1 -> PersonCastMoviesList(castMovies = it1) }
        }
    }
}