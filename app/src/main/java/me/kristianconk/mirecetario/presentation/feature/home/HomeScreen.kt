package me.kristianconk.mirecetario.presentation.feature.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import me.kristianconk.mirecetario.R
import me.kristianconk.mirecetario.domain.model.Recipe
import me.kristianconk.mirecetario.presentation.components.PageLoader
import me.kristianconk.mirecetario.presentation.components.RecipeSimpleRow
import me.kristianconk.mirecetario.presentation.components.SimpleErrorMessage
import me.kristianconk.mirecetario.presentation.utils.ConnectionState
import me.kristianconk.mirecetario.presentation.utils.connectivityState
import me.kristianconk.mirecetario.ui.theme.MiRecetarioTheme
import me.kristianconk.mirecetario.util.simpleRecipe

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@Composable
fun HomeScreen(
    recipePagingItems: LazyPagingItems<Recipe>,
    searchResult: List<Recipe>,
    actions: HomeActions
) {
    var searchQuery by remember {
        mutableStateOf("")
    }
    var searchActive by remember {
        mutableStateOf(false)
    }
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    BackHandler(enabled = true) {
        // no esta permitido el back
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

                SearchBar(
                    query = searchQuery,
                    onQueryChange = {
                        searchQuery = it
                        actions.onSearchChange(it)
                    },
                    onSearch = {
                        searchActive = false
                    },
                    active = searchActive,
                    onActiveChange = { searchActive = it },
                    placeholder = {
                        Text(text = stringResource(id = R.string.home_search_placeholder))
                    },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    if (searchQuery.isNotEmpty()) {
                        Column {
                            searchResult.forEach {
                                Text(text = it.title, modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        searchActive = false
                                        actions.onRecipeClick(it)
                                    })
                            }
                        }
                    }
                }

        },
        bottomBar = {
            if (!isConnected) {
                Text(
                    text = stringResource(id = R.string.home_internet_warning),
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxWidth()
                        .background(
                            Color.Red
                        ),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item { Spacer(modifier = Modifier.padding(4.dp)) }
            items(recipePagingItems.itemCount) { index ->
                RecipeSimpleRow(
                    recipe = recipePagingItems[index]!!,
                    onClick = actions.onRecipeClick
                )
            }
            recipePagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = recipePagingItems.loadState.refresh as LoadState.Error
                        item {
                            SimpleErrorMessage(
                                modifier = Modifier.fillParentMaxSize(),
                                message = getMessageError(error.error),
                                onClickRetry = { retry() })
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                            )
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = recipePagingItems.loadState.append as LoadState.Error
                        item {
                            SimpleErrorMessage(
                                modifier = Modifier,
                                message = getMessageError(error = error.error),
                                onClickRetry = { retry() })
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(4.dp)) }
        }
    }
}

@Composable
private fun getMessageError(error: Throwable): String {
    return if (error is java.net.UnknownHostException)
        stringResource(id = R.string.home_pager_error_internet)
    else stringResource(id = R.string.home_pager_error_generic)
}

@Preview
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    val flowdata = flow { emit(PagingData.from(listOf(simpleRecipe))) }
    MiRecetarioTheme {
        HomeScreen(
            flowdata.collectAsLazyPagingItems(),
            listOf(simpleRecipe),
            actions = HomeActions()
        )
    }
}

@Preview
@Composable
fun HomeScreenPreviewDark(modifier: Modifier = Modifier) {
    val flowdata = flow { emit(PagingData.from(listOf(simpleRecipe))) }
    MiRecetarioTheme(darkTheme = true) {
        HomeScreen(
            flowdata.collectAsLazyPagingItems(),
            listOf(simpleRecipe),
            actions = HomeActions()
        )
    }
}