package com.example.imdbapp.domain.model

import com.example.imdbapp.network.model.person.CastMovy
import com.example.imdbapp.network.model.person.KnownFor

data class Person(
    val awards: String?,
    val birthDate: String?,
    val castMovies: List<CastMovy>?,
    val deathDate: String?,
    val errorMessage: String?,
    val height: String?,
    val id: String?,
    val image: String?,
    val knownFor: List<KnownFor>?,
    val name: String?,
    val role: String?,
    val summary: String?,
    val isFavorite : Boolean = false
)