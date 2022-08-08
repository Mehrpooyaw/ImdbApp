package com.example.imdbapp.presentation.ui.reviews

sealed class ReviewsEvent{
    data class GetReviewsEvent(val id : String) : ReviewsEvent()
}