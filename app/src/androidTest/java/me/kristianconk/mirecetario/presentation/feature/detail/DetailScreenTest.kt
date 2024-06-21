package me.kristianconk.mirecetario.presentation.feature.detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import me.kristianconk.mirecetario.ui.theme.MiRecetarioTheme
import me.kristianconk.mirecetario.util.simpleRecipe
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testDetailContent() {
        var mapClicked = false
        composeTestRule.setContent {
            MiRecetarioTheme {
                DetailScreen(
                    recipe = simpleRecipe,
                    actions = DetailActions(onMapClick = { _, _, _ ->
                        mapClicked = true
                    })
                )
            }
        }
        composeTestRule.waitForIdle()
        // verificar se ve el titulo de la receta
        composeTestRule.onNodeWithText("Huevos pochados").assertIsDisplayed()
        // verificar ingrediente visible
        composeTestRule.onNodeWithText(simpleRecipe.ingredients[0]).assertIsDisplayed()
        // verificar instrucciones
        composeTestRule.onNodeWithText(simpleRecipe.steps[0]).assertIsDisplayed()
        // verificar visibilidad de boton mapa (primero se debe hacer scroll)
        composeTestRule.onNodeWithTag("MAP_BUTTON").performScrollTo()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("MAP_BUTTON").assertIsDisplayed()
        // verificar click en boton ver mapa
        composeTestRule.onNodeWithTag("MAP_BUTTON").performClick()
        composeTestRule.waitForIdle()
        assert(mapClicked)
    }
}