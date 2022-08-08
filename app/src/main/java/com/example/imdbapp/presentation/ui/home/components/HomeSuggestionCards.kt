package com.example.imdbapp.presentation.ui.home.components

import androidx.compose.foundation.layout.*
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imdbapp.domain.model.advanced_search.TopMovieType
import com.example.imdbapp.network.model.listItemMovies.Item
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeSuggestionCards(
    modifier : Modifier = Modifier,
    movies : List<Item>,
    onNavigateToMovieScreen : (String) -> Unit,
    onSeeMoreClicked : () -> Unit

) {
    HomeTitleAndSeeMoreRow(title = "Top 250 movies", onSeeMoreClick = onSeeMoreClicked)
    HomeMovie21x9Pager(
        movies = movies.subList(0, 6),
        onNavigateToMovieDetail = onNavigateToMovieScreen
    )

    Spacer(modifier = modifier.height(5.dp))
    Row(modifier.fillMaxWidth()) {
        Column(
            modifier
                .fillMaxWidth(0.5f)
                .padding(horizontal = 8.dp)
        ) {

            HomeDoubleMoviesPager(
                movies = movies.shuffled().subList(0, 6),
                onNavigateToMovieDetail = onNavigateToMovieScreen,
                spacerAlignment = Alignment.CenterEnd
            )
        }
        Column(
            modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 8.dp)
        ) {

            HomeDoubleMoviesPager(
                movies = movies.shuffled().subList(0, 6),
                onNavigateToMovieDetail = onNavigateToMovieScreen,
                spacerAlignment = Alignment.CenterStart
            )
        }
    }
}