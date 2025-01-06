package com.example.cookfresh.domain

data class MealModel (
    val id: Int,
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumb: String,
    val strIngredients: List<String>,
    val strMeasures: List<String>
)
