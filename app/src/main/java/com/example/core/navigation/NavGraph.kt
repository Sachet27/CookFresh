package com.example.core.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.auth.presentation.AuthActions
import com.example.auth.presentation.AuthState
import com.example.auth.presentation.AuthViewModel
import com.example.auth.presentation.components.AuthResponse
import com.example.auth.presentation.signin.LoginScreen
import com.example.auth.presentation.signup.SignUpScreen
import com.example.cookfresh.presentation.MealActions
import com.example.cookfresh.presentation.components.DrawerItems
import com.example.cookfresh.presentation.components.NavDrawer
import com.example.cookfresh.presentation.components.SideDrawerItemsList
import com.example.cookfresh.presentation.components.TopBar
import com.example.cookfresh.presentation.home.HomeScreen
import com.example.cookfresh.presentation.MealCategoryViewModel
import com.example.cookfresh.presentation.MealState
import com.example.cookfresh.presentation.about_us.AboutUsScreen
import com.example.cookfresh.presentation.meal_list.MealListScreen
import com.example.cookfresh.presentation.mealdetail.MealDescriptionScreen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CookFresh(
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val drawerState = DrawerState(DrawerValue.Closed)
    val authViewModel= viewModel<AuthViewModel>()
    val authState by authViewModel.state.collectAsStateWithLifecycle()
    val mealCategoryViewModel= koinViewModel<MealCategoryViewModel>()
    val mealState by mealCategoryViewModel.mealState.collectAsStateWithLifecycle()
    var isDrawerEnabled by remember { mutableStateOf(false) }
    LaunchedEffect(authState.isSignedIn) {
        if(authState.isSignedIn is AuthResponse.Authenticated)
            isDrawerEnabled= true
        else
            isDrawerEnabled= false
    }
    NavDrawer(
        enabled = isDrawerEnabled,
            drawerState = drawerState,
            items = SideDrawerItemsList.drawerItemsList,
            modifier = Modifier,
            onItemClick = { item ->
                when(item.id){
                    DrawerItems.HOME -> navController.navigate(NavRoutes.HomeRoute)
                    DrawerItems.ABOUT_US -> { navController.navigate(NavRoutes.AboutUsRoute)}
                    DrawerItems.I_FEEL_LUCKY -> {
                        mealCategoryViewModel.onAction(MealActions.onRandomMealClick)
                        navController.navigate(NavRoutes.MealDescriptionRoute)
                    }
                    DrawerItems.SIGN_OUT -> { authViewModel.onAction(AuthActions.SignOut) }
                }
                scope.launch {
                    drawerState.close()
                }
            }
        ) {
        NavHost(navController, startDestination = if(authState.isSignedIn is AuthResponse.Authenticated) SubGraph.CookFreshRoute else SubGraph.AuthRoute) {
            navigation<SubGraph.AuthRoute>( startDestination = NavRoutes.LoginRoute){
                composable<NavRoutes.LoginRoute> {
                    LoginScreen(
                        onAction = authViewModel::onAction,
                        state = authState,
                        onRegister = { navController.navigate(NavRoutes.SignUpRoute) },
                        onSignIn = {
                            navController.navigate(SubGraph.CookFreshRoute)
                        }
                    )
                }

                composable<NavRoutes.SignUpRoute> {
                    SignUpScreen(
                        onAction = authViewModel::onAction,
                        state = authState,
                        onRegisterComplete = { navController.navigateUp()}
                    )
                }
            }

            navigation<SubGraph.CookFreshRoute>(startDestination = NavRoutes.HomeRoute){
                composable<NavRoutes.HomeRoute>  {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background),
                        topBar = {
                            TopBar {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        }
                    ) { padding ->
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(padding)
                        ) {
                            HomeScreen(
                                state = mealState,
                                onAction = mealCategoryViewModel::onAction,
                                onNavToMealList = {
                                    navController.navigate(NavRoutes.CategoryDetailRoute)
                                }
                            )

                        }
                    }
                }
                composable<NavRoutes.CategoryDetailRoute> {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background),
                        topBar = {
                            TopBar {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        }
                    ) { padding ->
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(padding)
                        ) {
                            MealListScreen(
                                state = mealState,
                                onAction = mealCategoryViewModel::onAction,
                                onNavToDetailScreen = {
                                    navController.navigate(NavRoutes.MealDescriptionRoute)
                                }
                            )
                        }
                    }
                }

                composable<NavRoutes.MealDescriptionRoute> {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background),
                        topBar = {
                            TopBar {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        }
                    ) { padding ->
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(padding)
                        ) {
                            //detail screen
                            MealDescriptionScreen(
                                state = mealState,
                                onAction = {},
                                modifier = Modifier,
                            )

                        }
                    }
                }

                composable<NavRoutes.AboutUsRoute>  {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background),
                        topBar = {
                            TopBar {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        }
                    ) { padding ->
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(padding)
                        ) {
                            AboutUsScreen()
                        }
                    }
                }
            }

        }

        }
    }