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
import me.kristianconk.mirecetario.presentation.event.Event

class HomeViewModel(
    val getRecipesUseCase: GetRecipesUseCase,
    val searchRecipeUseCase: SearchRecipeUseCase
) : ViewModel() {
    private val _mainRecipeList = MutableStateFlow<PagingData<Recipe>>(PagingData.empty())
    val recipesData = _mainRecipeList.asStateFlow()

    private val _filteredRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val filteredRecipes = _filteredRecipes.asStateFlow()

    private val _sideEffects = MutableStateFlow(Event(""))
    val sideEffects = _sideEffects.asStateFlow()

    var selectedRecipe: Recipe? = null
        private set

    fun getRecipes() {
        viewModelScope.launch {
            getRecipesUseCase.execute().distinctUntilChanged().collect { data ->
                _mainRecipeList.value = data
            }
        }
    }

    fun selectRecipe(recipe: Recipe) {
        selectedRecipe = recipe
        _sideEffects.value = Event("detail")
    }

    fun searchRecipe(keyword: String) {
        viewModelScope.launch {
            searchRecipeUseCase.execute(keyword).collect {
                _filteredRecipes.value = it
            }
        }
    }

}