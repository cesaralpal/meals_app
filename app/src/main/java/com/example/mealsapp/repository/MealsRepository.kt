package com.example.mealsapp.repository

import android.util.Log
import com.example.mealsapp.model.*
import com.example.mealsapp.network.MealsApi
import com.example.mealsapp.util.Resource
import javax.inject.Inject

class MealsRepository @Inject constructor(private val api: MealsApi){

    suspend fun getCatalog() : Resource<List<Category>>{
       return  try {
           val categories  = api.getCatalog().categories
           Log.d("ItemList", "success ${categories.size}")
           Resource.Success(data = categories)

        }catch (exception: Exception){
           Log.d("catalog","failure ")
           Resource.Error(message = exception.message.toString())
        }
    }

    suspend fun getMeals(meal : String) : Resource<List<Meal>>{
        return  try {
            val meals  = api.getMeals(meal).meals
            Log.d("ItemList", "success meals ${meals.size}")
            Resource.Success(data = meals)

        }catch (exception: Exception){
            Log.d("meals","failure ")
            Resource.Error(message = exception.message.toString())
        }
    }

    suspend fun getMealsDetails(id : String) : Resource<List<MealX>>{
        return  try {
            val details  = api.getMealsDetails(id).meals
            Log.d("ItemList", "success details ${details.size}")
            Resource.Success(data = details)

        }catch (exception: Exception){
            Log.d("meals","failure ")
            Resource.Error(message = exception.message.toString())
        }
    }

    suspend fun getRandoomMeals() : Resource<List<MealX>>{
        return  try {
            val details  = api.getRandoom().meals
            Log.d("ItemList", "success details ${details.size}")
            Resource.Success(data = details)

        }catch (exception: Exception){
            Log.d("meals","failure ")
            Resource.Error(message = exception.message.toString())
        }
    }

}