package me.kristianconk.mirecetario.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.kristianconk.mirecetario.domain.model.OrderBy
import me.kristianconk.mirecetario.domain.model.Recipe
import me.kristianconk.mirecetario.domain.repository.MiRecetarioRepository

class GetRecipesUseCase(
    val repository: MiRecetarioRepository
) {
    suspend fun execute(): Flow<PagingData<Recipe>> {
        return repository.getRecipes(15)
    }
}