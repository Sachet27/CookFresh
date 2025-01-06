package com.example.cookfresh.data.networking.dto

import com.example.cookfresh.domain.MealModel
import kotlinx.serialization.Serializable

@Serializable
data class MealDescriptionResponseDto(
    val meals: List<MealDescriptionDto>
)