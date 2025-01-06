package com.example.core.navigation

import com.example.cookfresh.domain.MealCategoryModel
import kotlinx.serialization.Serializable

sealed interface NavRoutes {
    @Serializable
    data object HomeRoute: NavRoutes
    @Serializable
    data object CategoryDetailRoute: NavRoutes
    @Serializable
    data object LoginRoute: NavRoutes
    @Serializable
    data object SignUpRoute: NavRoutes
    @Serializable
    data object MealDescriptionRoute: NavRoutes
    @Serializable
    data object AboutUsRoute: NavRoutes
}