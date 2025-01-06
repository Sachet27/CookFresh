package com.example.core.navigation

import kotlinx.serialization.Serializable

sealed interface SubGraph {
    @Serializable
    data object AuthRoute: SubGraph
    @Serializable
    data object CookFreshRoute: SubGraph
}