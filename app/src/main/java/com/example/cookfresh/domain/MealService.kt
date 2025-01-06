package com.example.cookfresh.domain

import com.plcoding.cryptotracker.core.domain.util.NetworkError
import com.plcoding.cryptotracker.core.domain.util.Result

interface MealService {
    suspend fun getMealCategories(): Result<List<MealCategoryModel> , NetworkError>
    suspend fun getMeals( category: String ): Result<List<MealModel>, NetworkError>
    suspend fun getRandomMeal(): Result<MealModel, NetworkError>
    suspend fun getMealById(id: Int): Result<MealModel, NetworkError>
}