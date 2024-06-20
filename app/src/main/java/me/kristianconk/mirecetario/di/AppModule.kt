package me.kristianconk.mirecetario.di

import me.kristianconk.mirecetario.data.api.RecipeApi
import me.kristianconk.mirecetario.data.api.RetrofitFactory
import me.kristianconk.mirecetario.data.db.RecipeDao
import me.kristianconk.mirecetario.data.db.RecipeDatabase
import me.kristianconk.mirecetario.data.db.RecipeRemoteKeyDao
import me.kristianconk.mirecetario.data.repository.LocalDataSource
import me.kristianconk.mirecetario.data.repository.MiRecetarioRepositoryImp
import me.kristianconk.mirecetario.data.repository.RecipeRemoteMediator
import me.kristianconk.mirecetario.data.repository.RemoteDataSource
import me.kristianconk.mirecetario.domain.repository.MiRecetarioRepository
import me.kristianconk.mirecetario.domain.usecase.GetRecipesUseCase
import me.kristianconk.mirecetario.domain.usecase.SearchRecipeUseCase
import me.kristianconk.mirecetario.presentation.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    // === data ===
    // daos
    single<RecipeDao> { RecipeDatabase.getInstance(get()).recipeDao() }
    single<RecipeRemoteKeyDao> { RecipeDatabase.getInstance(get()).recipeRemoteKeyDao() }
    // retrofit
    single<Retrofit> { RetrofitFactory.create() }
    // api
    single { RecipeApi(get()) }
    // data source
    single { LocalDataSource(get(), get()) }
    single { RemoteDataSource(get()) }
    single { RecipeRemoteMediator(get(), get()) }
    // repo
    single<MiRecetarioRepository> { MiRecetarioRepositoryImp(get(), get(), get()) }
    // === domain ===
    factory { GetRecipesUseCase(get()) }
    factory { SearchRecipeUseCase(get()) }
    // === presentation ===
    viewModel { HomeViewModel(get(), get()) }
}