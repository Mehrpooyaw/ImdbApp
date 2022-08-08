package com.example.imdbapp.presentation.ui.person.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.network.model.person.KnownFor
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.ui.components.CustomTitleText
import com.example.imdbapp.presentation.ui.components.TwoLineMovieCard
import com.example.imdbapp.util.FakeData


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PersonKnownForList(
    modifier : Modifier = Modifier,
    onNavigateToMovieScreen : (String?) -> Unit,

    knownForList : List<KnownFor>
){
    Column(modifier.padding(vertical = 5.dp)) {
        CustomTitleText(text = "KNOWN FOR ...", modifier = modifier.padding(start = 15.dp))

        LazyRow(modifier.padding(vertical = 10.dp)) {

            items(knownForList) {
                TwoLineMovieCard(
                    imageContentDescription = "person_known_for_image",
                    imageUrl = it.image,
                    firstLine = it.fullTitle,
                    secondLine = it.role,
                    secondLineForImdbRate = null,
                    onNavigateToMovieScreen = onNavigateToMovieScreen,
                    movieId = it.id


                )
            }
        }
    }
}






@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PersonKnownForListPreview(){
    val person = FakeData.SamplePerson
    IMdbAppTheme() {
        Scaffold {
            person.knownFor?.let { it1 -> PersonKnownForList(knownForList = it1, onNavigateToMovieScreen = {}) }
        }
    }
}



