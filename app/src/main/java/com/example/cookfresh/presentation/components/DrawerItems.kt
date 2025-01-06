package com.example.cookfresh.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class DrawerItems {
    HOME, ABOUT_US, I_FEEL_LUCKY, SIGN_OUT;
    companion object{
        val list= listOf(HOME, ABOUT_US, I_FEEL_LUCKY, SIGN_OUT)
    }
}

fun DrawerItems.toSelectionString(): String{
    return when(this){
      DrawerItems.HOME-> "Home"
      DrawerItems.ABOUT_US-> "About Us"
      DrawerItems.I_FEEL_LUCKY-> "I Feel Lucky"
      DrawerItems.SIGN_OUT-> "Sign Out"
    }
}

fun DrawerItems.toIcon(): ImageVector{
    return when(this){
        DrawerItems.HOME-> Icons.Outlined.Home
        DrawerItems.ABOUT_US->Icons.Outlined.Person
        DrawerItems.I_FEEL_LUCKY-> Icons.Outlined.Star
        DrawerItems.SIGN_OUT-> Icons.AutoMirrored.Outlined.ExitToApp
    }
}

