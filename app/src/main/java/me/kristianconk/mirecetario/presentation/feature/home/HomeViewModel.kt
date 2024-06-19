package me.kristianconk.mirecetario.presentation.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import me.kristianconk.mirecetario.domain.model.Recipe
import me.kristianconk.mirecetario.domain.usecase.GetRecipesUseCase
import me.kristianconk.mirecetario.domain.usecase.SearchRecipeUseCase

class HomeViewModel(
    val getRecipesUseCase: GetRecipesUseCase,
    val searchRecipeUseCase: SearchRecipeUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow<PagingData<Recipe>>(PagingData.empty())
    val uiState = _uiState.asStateFlow()

    fun getRecipes() {
        viewModelScope.launch {
            getRecipesUseCase.execute().distinctUntilChanged().collect { data ->
                _uiState.value = data
            }
        }
    }
}