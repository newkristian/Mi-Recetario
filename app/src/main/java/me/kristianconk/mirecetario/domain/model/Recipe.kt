package me.kristianconk.mirecetario.domain.model

import java.util.Date

data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val urlPhoto: String?,
    val ingredients: List<String>,
    val steps: List<String>,
    val notes: String,
    val creationDate: Date,
    val latitude: Double?,
    val longitude: Double?
)
