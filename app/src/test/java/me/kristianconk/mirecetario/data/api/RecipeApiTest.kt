package me.kristianconk.mirecetario.data.api

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import me.kristianconk.mirecetario.mocks.recipeDTOMock
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class RecipeApiTest {

    lateinit var systemUnderTest: RecipeApi

    @MockK(relaxed = true)
    lateinit var apiService: RecipeApiService


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        systemUnderTest = RecipeApi(apiService)
    }

    @Test
    fun testGetRecipesSuccessForPageOne() = runTest {
        // given
        coEvery { apiService.getRecipes() } returns listOf(recipeDTOMock)
        // when
        val res = systemUnderTest.getRecipes(1)
        // then
        assert(res.isNotEmpty())
    }

    @Test
    fun testGetRecipesEmptyForPageHundred() = runTest {
        // given
        coEvery { apiService.getRecipes() } returns listOf(recipeDTOMock)
        // when
        val res = systemUnderTest.getRecipes(100)
        // then
        assert(res.isEmpty())
    }

    @Test
    fun testGetRecipesError() = runTest {
        // given
        coEvery { apiService.getRecipes() } throws Exception("forced exception")
        try {
            // when
            val res = systemUnderTest.getRecipes(1)
        } catch(ex: Exception){
            // then
            Assert.assertEquals("forced exception", ex.message)
        }
    }

    @After
    fun tearDown() {

    }
}