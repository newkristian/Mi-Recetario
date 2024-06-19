package me.kristianconk.mirecetario.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import me.kristianconk.mirecetario.presentation.feature.detail.DetailActions
import me.kristianconk.mirecetario.presentation.feature.detail.DetailScreen
import me.kristianconk.mirecetario.presentation.feature.home.HomeActions
import me.kristianconk.mirecetario.presentation.feature.home.HomeScreen
import me.kristianconk.mirecetario.presentation.feature.home.HomeViewModel

@Composable
fun HomeNavHost(
    homeViewModel: HomeViewModel
) {
    val navController = rememberNavController()
    val sideEffect = homeViewModel.sideEffects.collectAsState().value
    LaunchedEffect(key1 = sideEffect) {
        sideEffect.getIfNotConsumed()?.let {
            if ((it as String) == "detail") {
                navController.navigate("detail")
            }

        }
    }
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            val lazyPagingItems = homeViewModel.uiState.collectAsLazyPagingItems()
            LaunchedEffect(key1 = Unit) {
                homeViewModel.getRecipes()
            }
            HomeScreen(
                recipePagingItems = lazyPagingItems,
                actions = HomeActions(onRecipeClick = homeViewModel::selectRecipe)
            )
        }
        composable("detail") {
            homeViewModel.selectedRecipe?.let { recipe ->
                DetailScreen(
                    recipe = recipe,
                    actions = DetailActions(
                        onBackClick = { navController.popBackStack() })
                )
            } ?: run {
                navController.popBackStack()
            }
        }
        /*composable(
            "map/{lat}/{lon}",
            arguments = listOf(navArgument("lat") { type = NavType.StringType },
                navArgument("lon") { type = NavType.StringType })) {
            MapScreen()
        }*/
    }
}
