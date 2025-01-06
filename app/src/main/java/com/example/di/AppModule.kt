package com.example.di

import com.example.core.data.networking.HttpClientFactory
import com.example.cookfresh.data.networking.MealApiService
import com.example.cookfresh.domain.MealService
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import com.example.cookfresh.presentation.MealCategoryViewModel

val appModule= module {
    single {
        HttpClientFactory.create(CIO.create())
    }
    singleOf(::MealApiService).bind<MealService>()
    viewModelOf(::MealCategoryViewModel)
//    viewModelOf(::AuthViewModel)
}