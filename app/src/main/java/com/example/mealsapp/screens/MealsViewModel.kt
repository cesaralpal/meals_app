package com.example.mealsapp.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsapp.model.Category
import com.example.mealsapp.model.Meal
import com.example.mealsapp.model.MealX
import com.example.mealsapp.repository.MealsRepository
import com.example.mealsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(private val repository: MealsRepository): ViewModel() {

    var list: List<Category> by mutableStateOf(listOf())
    var meals: List<Meal> by mutableStateOf(listOf())
    var details: List<MealX> by mutableStateOf(listOf())
    var randoomMeal: List<MealX> by mutableStateOf(listOf())

    var state: Boolean by mutableStateOf(true)
    var stateDetails: Boolean by mutableStateOf(true)

    init {
       getSims()
        getRandoomMeal()
    }

    private fun getSims() {
        viewModelScope.launch {
            try {
                when(val response = repository.getCatalog()) {
                    is Resource.Success -> {
                        Log.d("MainViewModel", "fetchBooks: Success")
                        state = false
                        list = response.data!!
                    }
                    is Resource.Error -> {
                        state = false
                        Log.d("TAG", "fetchBooks: Failure")
                    }
                    else -> {}
                }

            }catch (e: Exception){
                Log.d("Exce", "searchBooks: ${e.localizedMessage}")}
        }
    }

     fun getMeals(meal : String) {
        viewModelScope.launch {
            try {
                when(val response = repository.getMeals(meal)) {
                    is Resource.Success -> {
                        Log.d("MainViewModel", "fetchBooks: Success")
                        state = false
                        meals = response.data!!
                    }
                    is Resource.Error -> {
                        state = false
                        Log.d("TAG", "fetchBooks: Failure")
                    }
                    else -> {}
                }

            }catch (e: Exception){
                Log.d("Exce", "getMeals: ${e.localizedMessage}")}
        }
    }

    fun getMealsDetails(id : String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when(val response = repository.getMealsDetails(id)) {
                    is Resource.Success -> {
                        Log.d("MainViewModel", "fetchDetails: Success")
                        stateDetails = false
                        details = response.data!!
                    }
                    is Resource.Error -> {
                        stateDetails = false
                        Log.d("TAG", "fetchDetails: Failure")
                    }
                    else -> {}
                }

            }catch (e: Exception){
                Log.d("Exce", "getMeals: ${e.localizedMessage}")}
        }
    }

    fun getRandoomMeal() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when(val response = repository.getRandoomMeals()) {
                    is Resource.Success -> {
                        Log.d("MainViewModel", "fetchDetails: Success")
                        randoomMeal = response.data!!
                    }
                    is Resource.Error -> {
                        Log.d("TAG", "fetchDetails: Failure")
                    }
                    else -> {}
                }

            }catch (e: Exception){
                Log.d("Exce", "getMeals: ${e.localizedMessage}")}
        }
    }
}