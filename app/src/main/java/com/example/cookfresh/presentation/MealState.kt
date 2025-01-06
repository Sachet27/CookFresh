package com.example.cookfresh.presentation

import androidx.compose.runtime.Immutable
import com.example.cookfresh.domain.MealCategoryModel
import com.example.cookfresh.domain.MealModel

@Immutable
data class MealState(
    val isLoading: Boolean= false,
    val mealCategoryList: List<MealCategoryModel> = emptyList(),
    val selectedMealCategory: MealCategoryModel?= null,
    val meals: List<MealModel> = emptyList(),
    val mealDescription: MealModel?= null
)
