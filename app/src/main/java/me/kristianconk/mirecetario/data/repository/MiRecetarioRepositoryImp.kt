package me.kristianconk.mirecetario.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.RemoteMediator
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import me.kristianconk.mirecetario.data.db.toDomain
import me.kristianconk.mirecetario.domain.model.Recipe
import me.kristianconk.mirecetario.domain.repository.MiRecetarioRepository

@OptIn(ExperimentalPagingApi::class)
class MiRecetarioRepositoryImp(
    val local: LocalDataSource,
    val remote: RemoteDataSource,
    val remoteMediator: RecipeRemoteMediator
) : MiRecetarioRepository {


    override suspend fun getRecipes(pageSize: Int): Flow<PagingData<Recipe>> = Pager(
        config = PagingConfig(pageSize = pageSize, enablePlaceholders = true),
        remoteMediator = remoteMediator,
        pagingSourceFactory = { local.recipes() }
    ).flow.map {pagingData ->
        pagingData.map { it.toDomain() }
    }

    override suspend fun searchRecipe(keyword: String): Flow<List<Recipe>> {
        return flow {
            // se añade % para facilitar busqueda en sql
            val searchResult = local.searchRecipe("%$keyword%")
            emit(searchResult)
        }
    }
}