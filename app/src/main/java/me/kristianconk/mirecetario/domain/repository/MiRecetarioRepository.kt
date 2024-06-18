package me.kristianconk.mirecetario.domain.repository

import kotlinx.coroutines.flow.Flow
import me.kristianconk.mirecetario.domain.model.Recipe

interface MiRecetarioRepository {

    suspend fun getRecipes(initialPage: Int): Flow<List<Recipe>>

    suspend fun searchRecipe(keyword: String): Flow<List<Recipe>>

}
