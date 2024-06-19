package me.kristianconk.mirecetario.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.kristianconk.mirecetario.domain.model.Recipe

class RecipePagingSource(
    val remoteDataSource: RemoteDataSource
) : PagingSource<Int, Recipe>() {
    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val currentPage = params.key ?: 1
        val recipes = remoteDataSource.getRecipes(currentPage)
        return LoadResult.Page(
            data = recipes,
            prevKey = if (currentPage == 1) null else currentPage - 1,
            nextKey = if (recipes.isEmpty()) null else currentPage + 1
        )
    }
}