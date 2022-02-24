package com.example.mealsapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.mealsapp.screens.MealsCategories
import com.example.mealsapp.screens.MealsDetails
import com.example.mealsapp.screens.MealsHome
import com.example.mealsapp.screens.SplashScreen

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun MealsNavigation() {
    val navController= rememberNavController()
    NavHost(navController = navController,
        startDestination = MealsScreens.SplashScreen.name ){

        composable(MealsScreens.SplashScreen.name) {
          SplashScreen(navController = navController)


        }
        composable(MealsScreens.CatalogScreen.name) {
            MealsHome(navController = navController)
        }
        
        composable(MealsScreens.MealsScreen.name+"/{category}", arguments = listOf(navArgument(name = "category"){type = NavType.StringType})){
            backStackEntry ->
            MealsCategories(navController = navController,
                meal = backStackEntry.arguments?.getString("category","")
            )
        }

        composable(MealsScreens.MealsDetails.name+"/{id}", arguments = listOf(navArgument(name = "id"){type = NavType.StringType})){
                backStackEntry ->
            MealsDetails(
                id = backStackEntry.arguments?.getString("id","")
            )
        }
    }
}