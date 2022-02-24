package com.example.mealsapp.network

import com.example.mealsapp.model.Categories
import com.example.mealsapp.model.Meals
import com.example.mealsapp.model.MealsDetails
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface MealsApi {

    @GET("categories.php")
    suspend fun getCatalog() : Categories

    @GET("filter.php")
    suspend fun getMeals(@Query("c") meal: String?) : Meals

    @GET("lookup.php")
    suspend fun getMealsDetails(@Query("i") id: String?) : MealsDetails

    @GET("random.php")
    suspend fun getRandoom() : MealsDetails
}