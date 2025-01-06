package com.example.auth.presentation

import android.content.Context

interface AuthActions {
    data class SignIn(val context: Context): AuthActions
    data object SignOut: AuthActions
    data class OnEmailChange(val newEmail: String): AuthActions
    data class OnPasswordChange(val newPassword: String): AuthActions
    data class OnSignUpWithEmail(val context: Context): AuthActions
    data class OnSignInWithEmail(val context: Context): AuthActions
    data object OnSignOut: AuthActions
}