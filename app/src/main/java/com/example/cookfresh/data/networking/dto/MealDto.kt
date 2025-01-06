package com.example.cookfresh.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealDto(
    val idMeal: Int,
    val strMeal: String,
    val strMealThumb: String
)
