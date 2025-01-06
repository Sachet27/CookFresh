package com.example.cookfresh.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealCategoriesResponseDto(
    val categories: List<MealCategoryDto>
)
