package com.example.imdbapp.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apiKeys")
data class ApiKey(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int =0,

    @ColumnInfo(name = "string_id")
    val stringId : String,

    @ColumnInfo(name = "name")
    val name : String? = null
)