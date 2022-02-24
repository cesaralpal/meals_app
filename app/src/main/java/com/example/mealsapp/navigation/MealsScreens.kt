package com.example.mealsapp.navigation

enum class MealsScreens {
    SplashScreen,
    CatalogScreen,
    MealsScreen,
    MealsDetails;
    companion object {
        fun fromRoute(route: String?): MealsScreens
                = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            CatalogScreen.name -> CatalogScreen
            MealsScreen.name -> MealsScreen
            MealsDetails.name -> MealsDetails
            null -> CatalogScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}