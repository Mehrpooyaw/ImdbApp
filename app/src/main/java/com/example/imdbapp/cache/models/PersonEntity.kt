package com.example.imdbapp.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_person")
data class PersonEntity(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val name: String?,
    val role: String?,
    val image : String?
)
