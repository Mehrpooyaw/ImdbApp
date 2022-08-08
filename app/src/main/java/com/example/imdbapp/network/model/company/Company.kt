package com.example.propermates.network.model.company

data class Company(
    val errorMessage: String?,
    val id: String?,
    val items: List<Item>?,
    val name: String?
)