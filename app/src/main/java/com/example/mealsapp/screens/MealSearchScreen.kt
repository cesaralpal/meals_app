package com.example.mealsapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mealsapp.widgets.InputField
import com.example.mealsapp.widgets.MealsAppBar
import java.util.*


@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    label: String = "search",
    navController: NavController,
    viewModel: MealsViewModel = hiltViewModel(),
                ) {

    Scaffold(
        topBar = {
            MealsAppBar(title = "Search books", icon = Icons.Default.ArrowBack, showProfile = false){
                //navController.navigate(ReaderScreens.ReaderHomeScreen.name)
            }

        },)

    {
        Surface() {

            Column {

                SearchForm(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)){
                }

                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }
}
@ExperimentalComposeUiApi
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    hint: String = "Search",
    onSearch: (String) -> Unit = {}) {
    Column() {
        val searchQuery = rememberSaveable{ mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQuery.value) {
            searchQuery.value.trim().isNotEmpty()
        }
        InputField(
            valueState = searchQuery, labelId = "Search" ,
            enabled = true, onAction = KeyboardActions {
                //The submit button is disabled unless the inputs are valid. wrap this in if statement to accomplish the same.
                if (!valid) return@KeyboardActions
                onSearch(searchQuery.value.trim())
                searchQuery.value = ""
                keyboardController?.hide() //(to use this we need to use @ExperimentalComposeUiApi
            } )

    }

}





