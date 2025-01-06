package com.example.auth.presentation.components

interface AuthResponse{
    data object Authenticated: AuthResponse
    data object Unauthenticated: AuthResponse
    data object Loading: AuthResponse
    data class Error( val message: String): AuthResponse
}