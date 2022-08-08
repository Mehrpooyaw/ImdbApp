package com.example.imdbapp.presentation.ui.reviews

import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import com.example.imdbapp.presentation.ui.movie.components.MovieReviewsList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ReviewsScreen(
    viewModel: ReviewsViewModel,
    id : String?
) {
    val onLoad = viewModel.onLoad
    val loading = viewModel.loading
    val reviews = viewModel.reviews
    if (!viewModel.onLoad.value ) {
        onLoad.value = true
        id?.let {
            viewModel.onTriggerEvent(ReviewsEvent.GetReviewsEvent(it) )

        }}
    reviews.value?.let {
        MovieReviewsList(reviews = it)
    }
}