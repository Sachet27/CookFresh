package com.example.cookfresh.data.mappers

import com.example.cookfresh.data.networking.dto.MealCategoryDto
import com.example.cookfresh.data.networking.dto.MealDescriptionDto
import com.example.cookfresh.data.networking.dto.MealDto
import com.example.cookfresh.data.networking.dto.MealResponseDto
import com.example.cookfresh.domain.MealCategoryModel
import com.example.cookfresh.domain.MealModel

fun MealCategoryDto.toMealCategoryModel(): MealCategoryModel{
    return MealCategoryModel(
        id = idCategory,
        name = strCategory,
        description = strCategoryDescription,
        thumb = strCategoryThumb
    )
}
fun MealDto.toMealModel(): MealModel {
    return MealModel(
        id = idMeal,
        name = strMeal,
        category = "",
        area = "",
        instructions = "",
        thumb = strMealThumb,
        strIngredients = emptyList(),
        strMeasures = emptyList()
    )
}

fun MealDescriptionDto.toMealModel(): MealModel{
    val ingredientsList= listOf(strIngredient1,strIngredient2,strIngredient3,strIngredient4,strIngredient5,strIngredient6).filterNot { it.isNullOrBlank()}.map { it!! }
    val measuresList= listOf(strMeasure1,strMeasure2,strMeasure3,strMeasure4,strMeasure5,strMeasure6).filterNot { it.isNullOrBlank() }.map { it!! }

    return MealModel(
        id = idMeal,
        name = strMeal,
        category = strCategory,
        area = strArea,
        instructions = strInstructions,
        thumb = strMealThumb,
        strIngredients = ingredientsList,
        strMeasures = measuresList
    )
}