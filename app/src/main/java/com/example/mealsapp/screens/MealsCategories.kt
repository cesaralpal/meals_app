package com.example.mealsapp.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mealsapp.components.Meals

@ExperimentalAnimationApi
@Composable
fun MealsCategories(navController: NavController, viewModel: MealsViewModel = hiltViewModel(), meal: String?) {

    Meals(navController = navController, viewModel = viewModel, meal = meal)

}