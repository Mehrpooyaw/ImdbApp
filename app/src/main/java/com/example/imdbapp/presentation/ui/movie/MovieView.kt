package com.example.imdbapp.presentation.ui.movie

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.domain.model.Movie
import com.example.imdbapp.network.model.movie.Actor
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.ui.components.CustomDivider
import com.example.imdbapp.presentation.ui.components.CustomTitleText
import com.example.imdbapp.presentation.ui.loading_and_default_views.DefaultBottomPageView
import com.example.imdbapp.presentation.ui.movie.components.*
import com.example.imdbapp.util.FakeData
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieView(
    movie : Movie,
    modifier: Modifier = Modifier,
    images : List<String>?,
    onPersonClicked : (String?) -> Unit,
    onMovieClicked : (String?) -> Unit,
    onNavigateToActorsScreen :(List<Actor>?) -> Unit,
    onNavigateToCompanyScreen : (String?) -> Unit,
    onNavigateToReviewsScreen : (String) -> Unit,
    onNavigateToWikipediaScreen : () -> Unit,
    onNavigateToTrailerScreen : (String) -> Unit,
    onFavoriteButtonClicked : (Movie) -> Unit,
    isFavorite : Boolean,
    onBackPressed : () -> Unit

              ) {
    val verticalScroll = rememberScrollState()
    Scaffold(backgroundColor = MaterialTheme.colors.background) {




            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .verticalScroll(verticalScroll),

                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                MovieImageAndBaseInfoCard(
                    movie = movie, isFavorite = isFavorite, images = images,
                    onBackPressed = onBackPressed,
                    state = verticalScroll,
                    onFavoriteButtonClicked = onFavoriteButtonClicked
                    )

                if (movie.genreList != null || movie.contentRating != null) {
                    MovieCRandGenresRow(
                        genres = movie.genreList,
                        contentRating = movie.contentRating
                    )
                }

                movie.imDbRating?.let {
                    if (it != "") {
                        MovieImdbRateRow(imDbRate = it, imdbVotes = movie.imDbRatingVotes)
                    }
                }
                movie.plot?.let {
                    MoviePlot(plot = it)


                }
                movie.actorList?.let {
                    Column() {
                        MiniActorsRow(
                            actorsList = it,
                            onPersonClicked = onPersonClicked,
                            onNavigateToActorsScreen = onNavigateToActorsScreen,
                            scrollState = verticalScroll
                        )
                        if (movie.directorList != null && movie.writerList != null) {
                            MovieDirectorsAndWritersRowList(
                                movie = movie,
                                onClick = onPersonClicked
                            )
                        }
                    }
                }
                CustomDivider()
                MovieAwardsView(movie = movie)
                CustomDivider()
                movie.trailer?.linkEmbed?.let {
                    CustomTitleText(text = "${movie.title} Trailer")
                    MovieTrailerThumbsCard(
                        modifier = modifier,
                        imageUrl = movie.trailer.thumbnailUrl,
                        onClick = onNavigateToTrailerScreen,
                        imdbTrailer = it
                    )
                }


                if (movie.companyList != null || movie.boxOffice != null) {
                    MovieCompaniesAndBoxOffice(
                        boxOffice = movie.boxOffice,
                        companies = movie.companyList
                    )
                }
                MovieInfoView(movie = movie)
                Spacer(modifier.height(10.dp))
                movie.similars?.let { similarMovies ->
                    SimilarMoviesRowList(
                        similarMovies = similarMovies,
                        onMovieClicked = onMovieClicked
                    )
                }
                movie.id?.let {
                    CustomDivider()
                    MovieReviewsButton(onClick = onNavigateToReviewsScreen, id = it)

                }
                CustomDivider(modifier = modifier.padding(vertical = 10.dp))
                DefaultBottomPageView()
            }


    }
}



@ExperimentalPagerApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieViewPreview(){
    val fakeData = FakeData
    fakeData.SampleMovie.reviews = fakeData.sampleReviews
    IMdbAppTheme() {
        MovieView(fakeData.SampleMovie, onPersonClicked = {}, onMovieClicked = {}, onNavigateToActorsScreen = {}, images = listOf(""), onNavigateToCompanyScreen = {}, onNavigateToReviewsScreen = {}, onNavigateToWikipediaScreen = {},
            onNavigateToTrailerScreen = {}, onFavoriteButtonClicked = {}, onBackPressed = {}, isFavorite = true)
    }

}


