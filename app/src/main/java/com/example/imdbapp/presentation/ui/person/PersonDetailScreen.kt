package com.example.imdbapp.presentation.ui.person

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import com.example.imdbapp.presentation.ui.person.components.PersonLoadingView

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PersonDetailScreen(
    personId : String?,
    viewModel : PersonViewModel,
    onNavigateToMovieScreen : (String?) -> Unit,
    onBackPressed : () -> Unit
){
    if (personId == null){
        // TODO: Show invalid PersonView
    }else {
        val onLoad = viewModel.onLoad.value
        if (!onLoad) {
            viewModel.onLoad.value = true
            viewModel.onTriggerEvent(PersonEvent.GetPersonEvent(personId))
        }


        val loading = viewModel.onLoad.value
        val person = viewModel.person.value
        val isFavorite = viewModel.isFavorite.value

        val scaffoldState = rememberScaffoldState()

        Scaffold(
            scaffoldState = scaffoldState
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                if (loading && person == null) {
                    PersonLoadingView(onBackPressed = onBackPressed)
                } else if (!loading && person == null && onLoad) {
                    // Show Invalid Name
                } else {
                    if (person != null) {
                        PersonView(
                            person = person,
                            onNavigateToMovieScreen = onNavigateToMovieScreen,
                            onBack = onBackPressed, isFavorite = isFavorite,
                            onFavoriteButtonClicked = viewModel::onFavoriteButtonClicked
                            )
                    }
                }
            }
        }
    }
}
