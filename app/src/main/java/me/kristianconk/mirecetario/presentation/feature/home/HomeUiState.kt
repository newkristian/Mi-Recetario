package me.kristianconk.mirecetario.presentation.feature.home

import androidx.paging.PagingData
import me.kristianconk.mirecetario.domain.model.Recipe

data class HomeUiState(
    val isLoading: Boolean = true,
    val recipes: PagingData<Recipe> = PagingData.empty()
)

data class HomeActions(
    val onSearchChange: (String) -> Unit = {},
    val onRecipeClick: (Recipe) -> Unit = {}
)