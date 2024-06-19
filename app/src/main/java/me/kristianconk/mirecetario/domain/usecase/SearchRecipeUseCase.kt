package me.kristianconk.mirecetario.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.kristianconk.mirecetario.domain.model.Recipe
import me.kristianconk.mirecetario.domain.repository.MiRecetarioRepository

class SearchRecipeUseCase(
    val repository: MiRecetarioRepository
) {
    suspend fun execute(query: String): Flow<List<Recipe>> {
        return repository.searchRecipe(query)
    }
}