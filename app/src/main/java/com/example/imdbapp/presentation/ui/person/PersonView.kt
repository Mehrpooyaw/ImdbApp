package com.example.imdbapp.presentation.ui.person

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.domain.model.Person
import com.example.imdbapp.presentation.theme.AlmostSilver
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.ui.loading_and_default_views.DefaultBottomPageView
import com.example.imdbapp.presentation.ui.person.components.PersonCastMoviesList
import com.example.imdbapp.presentation.ui.person.components.PersonImageAndBaseDetails
import com.example.imdbapp.presentation.ui.person.components.PersonKnownForList
import com.example.imdbapp.util.FakeData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PersonView(
    modifier : Modifier = Modifier,
    person : Person,
    onNavigateToMovieScreen : (String?) -> Unit,
    onBack : () -> Unit,
    isFavorite : Boolean,
    onFavoriteButtonClicked : (Person) -> Unit

    ){
    val verticalScroll = rememberScrollState()
    Scaffold {
        Column(modifier = modifier.verticalScroll(verticalScroll)) {
            PersonImageAndBaseDetails(
                person = person,onBack = onBack, onFavoriteButtonClicked = onFavoriteButtonClicked, isFavorite = isFavorite)
            person.knownFor?.let { it1 ->
                PersonKnownForList(knownForList = it1, onNavigateToMovieScreen = onNavigateToMovieScreen)
                Divider(
                    modifier = modifier.padding(10.dp).fillMaxWidth(), thickness = 1.dp,
                    color = AlmostSilver
                )

            }
            person.castMovies?.let { it2 ->
                PersonCastMoviesList(castMovies = it2)
                Divider(
                    modifier = modifier.padding(10.dp).fillMaxWidth(), thickness = 1.dp,
                    color = AlmostSilver
                )
            }
            Divider(modifier = modifier.padding(vertical = 10.dp))
            DefaultBottomPageView()
        }


    }
}



@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PersonViewPreview(){
    val samplePerson = FakeData.SamplePerson
    IMdbAppTheme(darkTheme = true) {
        PersonView(
            person = samplePerson,
            onNavigateToMovieScreen = {},
            onFavoriteButtonClicked = {},
            isFavorite = true,
            onBack = {}
        )
    }
}