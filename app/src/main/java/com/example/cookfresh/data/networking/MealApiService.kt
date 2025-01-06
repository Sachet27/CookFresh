package com.example.cookfresh.data.networking

import android.util.Log
import com.example.cookfresh.data.mappers.toMealCategoryModel
import com.example.cookfresh.data.mappers.toMealModel
import com.example.cookfresh.data.networking.dto.MealCategoriesResponseDto
import com.example.cookfresh.data.networking.dto.MealDescriptionResponseDto
import com.example.cookfresh.data.networking.dto.MealResponseDto
import com.example.cookfresh.domain.MealCategoryModel
import com.example.cookfresh.domain.MealModel
import com.example.cookfresh.domain.MealService
import com.example.core.data.networking.constructUrl
import com.example.core.data.networking.safeCall
import com.plcoding.cryptotracker.core.domain.util.NetworkError
import com.plcoding.cryptotracker.core.domain.util.Result
import com.plcoding.cryptotracker.core.domain.util.map
import com.plcoding.cryptotracker.core.domain.util.onError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MealApiService(
    private val httpClient: HttpClient
) : MealService {
    override suspend fun getMealCategories(): Result<List<MealCategoryModel>, NetworkError> {

        return safeCall<MealCategoriesResponseDto> {
            httpClient.get(
                urlString = constructUrl("/1/categories.php")
            )
        }.map { response ->
            response.categories.map {
                it.toMealCategoryModel()
            }
        }
    }

    override suspend fun getMeals(category: String): Result<List<MealModel>, NetworkError> {
        return safeCall<MealResponseDto> {
            httpClient.get(
                urlString = constructUrl("/1/filter.php?c=$category")
            )
        }.map { response ->
            response.meals.map {
                it.toMealModel()
            }
        }
    }

    override suspend fun getRandomMeal(): Result<MealModel, NetworkError> {
        return safeCall<MealDescriptionResponseDto> {
            httpClient.get(
                urlString = constructUrl("/1/random.php")
            )
        }.map { response ->
            response.meals[0].toMealModel()
        }
    }

    override suspend fun getMealById(id: Int): Result<MealModel, NetworkError> {
        return safeCall<MealDescriptionResponseDto> {
            httpClient.get(
                urlString = constructUrl("/1/lookup.php?i=$id")
            )
        }.map { response->
            response.meals[0].toMealModel()
        }
    }
}

