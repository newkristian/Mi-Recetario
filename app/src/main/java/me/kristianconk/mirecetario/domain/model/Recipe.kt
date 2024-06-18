package me.kristianconk.mirecetario.domain.model

data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val urlPhoto: String?,
    val ingredients: List<String>,
    val steps: List<String>,
    val notes: String,
    val location: Pair<Long, Long>?
)
