package com.example.mealsapp.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.mealsapp.components.Details

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun MealsDetails(viewModel: MealsViewModel = hiltViewModel(), id: String?) {

    Details(viewModel,id)

}