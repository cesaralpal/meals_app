package com.example.mealsapp.components

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.mealsapp.screens.MealsViewModel
import com.example.mealsapp.screens.SearchForm
import com.example.mealsapp.widgets.CategoryRow
import com.example.mealsapp.widgets.MealRow
import com.example.mealsapp.widgets.circularProgresBarIndeterminate
import com.example.mealsapp.widgets.urlIntent


@ExperimentalComposeUiApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun Catalog(navController: NavController, viewModel: MealsViewModel) {

    val catalog = viewModel.list


    if (viewModel.state) {
        circularProgresBarIndeterminate(true)
    } else {
        circularProgresBarIndeterminate(false)
        LazyColumn {
            item {
                Column {

                    SearchForm(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            item {
                RandoomMeals(viewModel = viewModel)

            }
            catalog.let {
                items(it.toList()) { category ->
                    CategoryRow(navController, category = category)
                }
            }
        }
    }

}

@ExperimentalAnimationApi
@Composable
fun Meals(navController: NavController, viewModel: MealsViewModel, meal: String?) {

    viewModel.getMeals(meal!!)
    if (viewModel.state) {
        circularProgresBarIndeterminate(true)
    } else {
        circularProgresBarIndeterminate(false)
        LazyColumn {
            viewModel.meals.let {
                items(it) { meal ->
                    MealRow(meal = meal, navController = navController)
                }
            }

        }
    }

}

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun Details(viewModel: MealsViewModel, id: String?) {
    val openWebView = remember { mutableStateOf(false) }
    viewModel.getMealsDetails(id!!)
    if (viewModel.stateDetails) {
        circularProgresBarIndeterminate(true)
    } else {
        circularProgresBarIndeterminate(false)
        Log.d("details", "${viewModel.details}")

        if (viewModel.details.isNotEmpty()) {
            val details = viewModel.details[0]

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())

            ) {


                Image(
                    painter = rememberImagePainter(details.strMealThumb),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )


                details.strCategory?.let { Text(text = "Category: " + it) }
                details.strArea?.let { Text(text = "Area: " + it) }
                details.strSource?.let { Text(text = "Source: " + it) }
                details.strInstructions?.let { Text(text = "Instructions: " + it) }

                details.strYoutube?.let {
                    Text(text = it, color = Color.Blue, modifier = Modifier.clickable {
                        openWebView.value = true
                    })
                }

                if (openWebView.value) {
                    details.strYoutube?.let { urlIntent(it) }
                }
            }


        }

    }
}


@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun RandoomMeals(viewModel: MealsViewModel) {
    val openWebView = remember { mutableStateOf(false) }
    Log.d("randoommeal", "${viewModel.randoomMeal}")

    if (viewModel.randoomMeal.isNotEmpty()) {
        val details = viewModel.randoomMeal[0]


        Column {

            Image(
                painter = rememberImagePainter(details.strMealThumb),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )

            details.strCategory?.let { Text(text = "Category: " + it) }
            details.strArea?.let { Text(text = "Area: " + it) }
            details.strSource?.let { Text(text = "Source: " + it) }
            details.strYoutube?.let {
                Text(text = it, color = Color.Blue, modifier = Modifier.clickable {
                    openWebView.value = true
                })
            }

            if (openWebView.value) {
                details.strYoutube?.let { urlIntent(it) }
            }
        }

    }


}










