package me.kristianconk.mirecetario.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.kristianconk.mirecetario.domain.model.Recipe
import me.kristianconk.mirecetario.domain.repository.MiRecetarioRepository

class MiRecetarioRepositoryImp(
    val local: LocalDataSource,
    val remote: RemoteDataSource
) : MiRecetarioRepository {
    override suspend fun getRecipes(initialPage: Int): Flow<PagingData<Recipe>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = {
                RecipePagingSource(remote)
            }).flow
    }

    override suspend fun searchRecipe(keyword: String): Flow<List<Recipe>> {
        return flow {
            emit(emptyList())
        }
    }
}