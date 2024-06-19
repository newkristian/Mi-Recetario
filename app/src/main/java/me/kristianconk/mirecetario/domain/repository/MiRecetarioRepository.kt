package me.kristianconk.mirecetario.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.kristianconk.mirecetario.domain.model.Recipe

interface MiRecetarioRepository {

    suspend fun getRecipes(initialPage: Int): Flow<PagingData<Recipe>>

    suspend fun searchRecipe(keyword: String): Flow<List<Recipe>>

}
