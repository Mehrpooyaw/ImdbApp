package com.example.imdbapp.presentation.ui.person

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbapp.cache.AppDao
import com.example.imdbapp.datastore.SettingsDataStore
import com.example.imdbapp.domain.model.Movie
import com.example.imdbapp.domain.model.Person
import com.example.imdbapp.interactors.favorites.GetFavoritePerson
import com.example.imdbapp.interactors.person.GetPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


const val STATE_PERSON_KEY = "person.state.person.key"

@HiltViewModel
class PersonViewModel
@Inject
constructor(
    private val getPerson: GetPerson,
    private val setting : SettingsDataStore,
    private val state : SavedStateHandle,
    private val getFavoritePerson: GetFavoritePerson
) : ViewModel(){

    val person : MutableState<Person?> = mutableStateOf(null)

    val loading = mutableStateOf(false)

    val onLoad = mutableStateOf(false)
    
    val isFavorite = mutableStateOf(false)


    init {
        state.get<String>(STATE_PERSON_KEY)?.let { personId ->
            onTriggerEvent(PersonEvent.GetPersonEvent(personId))
        }
    }

    fun onFavoriteButtonClicked(person : Person){
        viewModelScope.launch {
            if (isFavorite.value) {
                isFavorite.value = false
                person.id?.let { removeFromFavorites(it) }
            } else {
                try {
                    insertToFavorite(person)
                    isFavorite.value = true
                } catch (e: Exception) {
                    Log.e("appDebug", e.message ?: "Unknown Error.")
                }
            }
        }
    }
    private suspend fun insertToFavorite(person: Person){
        getFavoritePerson.insertToFavorites(person = person)
    }

    private suspend fun removeFromFavorites(personId : String){
        getFavoritePerson.removeFromFavorites(id = personId)
    }


    fun onTriggerEvent(event: PersonEvent) {
        viewModelScope.launch {
            when(event){
                is PersonEvent.GetPersonEvent ->{
                    if (person.value == null){
                        getPerson(event.id)
                    }

                }
            }
        }

    }

    private fun getPerson(id: String) {
        getPerson.execute(apiKey = setting.apiKey.value, movieId = id).onEach { dataState ->
            loading.value = dataState.loading
            dataState.data?.let {
                person.value = it
                state.set(STATE_PERSON_KEY, it.id)
            }
            dataState.error?.let { error ->
                Log.e("AppDebug", "getPerson : ${error}")
                TODO("Handle Error Message")

            }

        }.launchIn(viewModelScope)
    }
}