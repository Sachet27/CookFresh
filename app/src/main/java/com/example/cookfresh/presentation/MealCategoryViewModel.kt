package com.example.cookfresh.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.cookfresh.domain.MealCategoryModel
import com.example.cookfresh.domain.MealService
import com.example.core.navigation.NavRoutes
import com.plcoding.cryptotracker.core.domain.util.onError
import com.plcoding.cryptotracker.core.domain.util.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MealCategoryViewModel(
    private val mealService: MealService
) : ViewModel(){

    private val _mealState = MutableStateFlow(MealState())
    val mealState= _mealState
        .onStart {
            getMealCategories()
            }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), MealState() )

    fun onAction(
        action: MealActions
    ){
        when(action){
            is MealActions.onMealClick -> getMealList(action.mealCategory)
            is MealActions.onMealItemClick -> getMealDescriptionById(action.id)
            is MealActions.onRandomMealClick-> getRandomMeal()
        }
    }
    private fun getMealCategories(){
        viewModelScope.launch (Dispatchers.IO){
            _mealState.update {
                it.copy(isLoading = true)
            }
                mealService.getMealCategories()
                    .onSuccess { mealCategories ->
                        _mealState.update {
                            it.copy(
                                isLoading = false,
                                mealCategoryList = mealCategories
                            )
                        }
                    }
                    .onError {error->
                        _mealState.update {
                            it.copy(
                            isLoading= false
                            )
                        }
                    }
            }
        }

    private fun getMealList(category: MealCategoryModel){
        _mealState.update {
            it.copy(
                selectedMealCategory = category
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            _mealState.update {
                it.copy(isLoading = true)
            }
            mealService.getMeals(category.name)
                .onSuccess { meals->
                    _mealState.update {
                        it.copy(
                            isLoading = false,
                            meals = meals
                        )
                    }
                }
                .onError { error->
                    _mealState.update {
                        it.copy(isLoading = false)
                        //error logic
                    }
                }
        }
    }

    private fun getMealDescriptionById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
         _mealState.update {
             it.copy(isLoading = true)
         }
         mealService.getMealById(id = id)
             .onSuccess { meal->
                 _mealState.update {
                     it.copy(
                         isLoading = false,
                         mealDescription = meal
                     )
                 }
             }
             .onError {
                 _mealState.update {
                     //halna baki xa
                     it.copy(isLoading = false)
                 }
             }
        }
    }

    private fun getRandomMeal(){

        viewModelScope.launch(Dispatchers.Main) {
            _mealState.update {
                it.copy(
                    isLoading = true,
                    mealDescription = null
                )
            }
            withContext(Dispatchers.IO) {
                mealService.getRandomMeal()
                    .onSuccess { meal ->
                        _mealState.update {
                            it.copy(
                                isLoading = false,
                                mealDescription = meal
                            )
                        }
                    }
                    .onError {
                        _mealState.update {
                            //halna baki xa
                            it.copy(isLoading = false)
                        }
                    }
            }
        }
    }

}