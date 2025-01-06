package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.auth.presentation.AuthViewModel
import com.example.auth.presentation.signin.LoginScreen
import com.example.cookfresh.presentation.about_us.AboutUsScreen
import com.example.cookfresh.presentation.components.TopBar
import com.example.core.navigation.CookFresh
import com.example.ui.theme.CookFreshTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CookFreshTheme {
                CookFresh(
                    navController = rememberNavController()
                )
                }
            }
        }
    }