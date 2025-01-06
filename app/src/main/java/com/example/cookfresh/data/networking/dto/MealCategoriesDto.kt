package com.example.cookfresh.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealCategoryDto (
    val idCategory: Int,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)