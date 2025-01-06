package com.example.auth.presentation

import com.example.auth.presentation.components.AuthResponse

data class AuthState(
    val isSignedIn: AuthResponse = AuthResponse.Unauthenticated,
    val email: String= "",
    val password: String = ""
)