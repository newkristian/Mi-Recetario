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
import me.kristianconk.mirecetario.presentation.feature.map.MapScreen

@Composable
fun HomeNavHost(
    homeViewModel: HomeViewModel
) {
    val navController = rememberNavController()
    // uso el side effect como una forma de navegar evitando el error de doble navegacion por recomposicion
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
            val lazyPagingItems = homeViewModel.recipesData.collectAsLazyPagingItems()
            val filtered = homeViewModel.filteredRecipes.collectAsState().value
            LaunchedEffect(key1 = Unit) {
                homeViewModel.getRecipes()
            }
            HomeScreen(
                recipePagingItems = lazyPagingItems,
                searchResult = filtered,
                actions = HomeActions(
                    onRecipeClick = homeViewModel::selectRecipe,
                    onSearchChange = homeViewModel::searchRecipe
                )
            )
        }
        composable("detail") {
            // debido a que el objeto de receta es muy complejo no conviene descomponerlo y pasarlo
            // por argumentos en la navegación, en su lugar lo retengo en el viewmodel y lo accedo en
            // la pantalla detalle
            homeViewModel.selectedRecipe?.let { recipe ->
                DetailScreen(
                    recipe = recipe,
                    actions = DetailActions(
                        onBackClick = { navController.popBackStack() },
                        onMapClick = { lat, lon, title ->
                            navController.navigate("map/$lat/$lon/$title")
                        })
                )
            } ?: run {
                navController.popBackStack()
            }
        }
        composable(
            "map/{lat}/{lon}/{title}",
            arguments = listOf(navArgument("lat") { type = NavType.StringType },
                navArgument("lon") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType }
            )
        ) {
            // caso contrario al detalle, a la pantalla mapa solo le llegan 3 argumentos por lo que se pueden pasar
            // directamente como argumentos de navegación
            val latS = it.arguments?.getString("lat") ?: "0.0"
            val lonS = it.arguments?.getString("lon") ?: "0.0"
            val title = it.arguments?.getString("title") ?: ""
            MapScreen(latS.toDouble(), lonS.toDouble(), title)
        }
    }
}
