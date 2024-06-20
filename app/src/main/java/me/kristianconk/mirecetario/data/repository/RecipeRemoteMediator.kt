package me.kristianconk.mirecetario.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import me.kristianconk.mirecetario.data.db.RecipeEntity
import me.kristianconk.mirecetario.data.db.RecipeRemoteKeyEntity

private const val RECIPE_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class RecipeRemoteMediator(
    val remote: RemoteDataSource,
    val local: LocalDataSource
) : RemoteMediator<Int, RecipeEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RecipeEntity>
    ): MediatorResult {
        // primero calcular la siguiente pagina a partir del tipo de refresh y lo ultimo almacenado
        val page = when (loadType) {
            LoadType.REFRESH -> RECIPE_STARTING_PAGE_INDEX
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> local.getLastRemoteKey()?.nextPage ?: return MediatorResult.Success(
                endOfPaginationReached = true
            )
        }

        Log.d(
            "REPO",
            "RecipeRemoteMediator: load() called with: loadType = $loadType, page: $page, stateLastItem = ${state.isEmpty()}"
        )

        // debido a condiciones de carrera, el paginador puede solicitar la segunda pagina antes de que cargue la primera
        if (state.isEmpty() && page == 2) return MediatorResult.Success(endOfPaginationReached = false)

        try {
            // recuperar datos del remoto
            val recipes = remote.getRecipes(page)
            Log.d("XXX", "RecipeRemoteMediator: get recipes from remote")
            if (loadType == LoadType.REFRESH) {
                // si fue un full refresh borrar toda la base de datos
                local.clearRecipes()
                local.clearRemoteKeys()
            }
            // calcular y guardar la ultima pagina cargada
            val endOfPaginationReached = recipes.isEmpty()
            val prevPage = if (page == RECIPE_STARTING_PAGE_INDEX) null else page - 1
            val nextPage = if (endOfPaginationReached) null else page + 1
            val key = RecipeRemoteKeyEntity(prevPage = prevPage, nextPage = nextPage)
            local.saveRemoteKey(key)
            // guardar el contenido remoto en local
            local.saveRecipes(recipes)
            // devolver el resultado del mediador como exitoso
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (ex: Exception) {
            return MediatorResult.Error(ex)
        }
    }
}