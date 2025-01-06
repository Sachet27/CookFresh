package com.example.cookfresh.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealResponseDto (
    val meals: List<MealDto>
)