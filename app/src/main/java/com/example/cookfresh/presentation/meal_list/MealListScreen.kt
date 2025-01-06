package com.example.cookfresh.presentation.meal_list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.cookfresh.domain.MealCategoryModel
import com.example.cookfresh.domain.MealModel
import com.example.cookfresh.presentation.MealActions
import com.example.cookfresh.presentation.MealState
import com.example.cookfresh.presentation.meal_list.components.MealItem
import com.example.ui.theme.CookFreshTheme

@Composable
fun MealListScreen(
    state: MealState,
    onAction: (MealActions)->Unit,
    modifier: Modifier = Modifier,
    onNavToDetailScreen: ()->Unit
) {
    if(state.isLoading)
        Box(
            modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    else{
                LazyColumn(
                ) {
                    items(state.meals){ meal->
                        MealItem(
                            meal = meal,
                            onItemClick = {
                                onAction(MealActions.onMealItemClick(meal.id))
                                Log.d("Yeet", state.isLoading.toString())
                                onNavToDetailScreen()
                            },
                        )
                        HorizontalDivider()
                    }
                }
            }

        }


