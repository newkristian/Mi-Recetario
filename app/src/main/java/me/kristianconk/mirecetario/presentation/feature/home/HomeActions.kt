package me.kristianconk.mirecetario.presentation.feature.home

import me.kristianconk.mirecetario.domain.model.Recipe

data class HomeActions(
    val onSearchChange: (String) -> Unit = {},
    val onRecipeClick: (Recipe) -> Unit = {}
)