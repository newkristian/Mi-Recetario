package me.kristianconk.mirecetario.di

import me.kristianconk.mirecetario.data.api.RecipeApi
import me.kristianconk.mirecetario.data.api.RetrofitFactory
import me.kristianconk.mirecetario.data.repository.LocalDataSource
import me.kristianconk.mirecetario.data.repository.MiRecetarioRepositoryImp
import me.kristianconk.mirecetario.data.repository.RemoteDataSource
import me.kristianconk.mirecetario.domain.repository.MiRecetarioRepository
import me.kristianconk.mirecetario.domain.usecase.GetRecipesUseCase
import me.kristianconk.mirecetario.domain.usecase.SearchRecipeUseCase
import me.kristianconk.mirecetario.presentation.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    // data
    single<Retrofit> { RetrofitFactory.create() }
    single { RecipeApi(get()) }
    single { LocalDataSource() }
    single { RemoteDataSource(get()) }
    single<MiRecetarioRepository> { MiRecetarioRepositoryImp(get(), get()) }
    // domain
    factory { GetRecipesUseCase(get()) }
    factory { SearchRecipeUseCase(get()) }
    // presentation
    viewModel { HomeViewModel(get(), get()) }
}