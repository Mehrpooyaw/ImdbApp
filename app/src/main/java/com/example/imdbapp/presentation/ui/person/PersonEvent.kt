package com.example.imdbapp.presentation.ui.person

sealed class PersonEvent {
    data class GetPersonEvent(
        val id : String
    ):PersonEvent()
}