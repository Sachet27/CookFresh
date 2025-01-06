package com.example.cookfresh.presentation

import androidx.navigation.NavController
import com.example.cookfresh.domain.MealCategoryModel
import com.example.cookfresh.domain.MealModel

interface MealActions{
    data class onMealClick( val mealCategory: MealCategoryModel ): MealActions
    data class onMealItemClick(val id: Int): MealActions
    data object onRandomMealClick: MealActions
}
