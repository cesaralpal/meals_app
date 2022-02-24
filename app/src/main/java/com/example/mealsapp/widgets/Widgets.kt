package com.example.mealsapp.widgets

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.mealsapp.model.Category
import com.example.mealsapp.model.Meal
import com.example.mealsapp.navigation.MealsScreens
import com.example.mealsapp.screens.MealsViewModel

@ExperimentalAnimationApi
@Composable
fun circularProgresBarIndeterminate(visible : Boolean){

    AnimatedVisibility(visible = visible) {
        CircularProgressIndicator()
    }

}

@Composable
fun MealsLogo(modifier: Modifier = Modifier) {
    Text(text = "Meals",
        modifier = modifier.padding(bottom = 10.dp),
        style = MaterialTheme.typography.h3,
        color = Color.Red.copy(alpha = 0.5f))
}



@ExperimentalAnimationApi
@Composable
fun CategoryRow(navController: NavController, category: Category){

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        //.height(130.dp)
        .clickable {

            navController.navigate(route = MealsScreens.MealsScreen.name+"/${category.strCategory}")
        }
        , shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp,

                ) {

                val painter = rememberImagePainter(category.strCategoryThumb)

                val state = painter.state
                if (state is ImagePainter.State.Success ) {
                    circularProgresBarIndeterminate(visible = false) }
                else
                    circularProgresBarIndeterminate(visible = true)

                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )

            }

            Column(modifier = Modifier.padding(4.dp)) {

                Text(
                    text = category.idCategory,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Category: ${category.strCategory}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Description: ${category.strCategoryDescription}",
                    style = MaterialTheme.typography.caption
                )

            }

        }


    }

}

@ExperimentalAnimationApi
@Composable
fun MealRow(meal: Meal, navController: NavController){

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        //.height(130.dp)
        .clickable {
            navController.navigate(route = MealsScreens.MealsDetails.name+"/${meal.idMeal}")

        }
        , shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp,

                ) {

                val painter = rememberImagePainter(meal.strMealThumb)

                val state = painter.state
                if (state is ImagePainter.State.Success ) {
                    circularProgresBarIndeterminate(visible = false) }
                else
                    circularProgresBarIndeterminate(visible = true)

                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )

            }

            Column(modifier = Modifier.padding(4.dp)) {

                Text(
                    text = "Meal: ${meal.strMeal}",
                    style = MaterialTheme.typography.caption
                )

            }

        }


    }

}

@Composable
fun urlIntent(url:String){

    val webIntent: Intent = Uri.parse(url).let { webpage ->
        Intent(Intent.ACTION_VIEW, webpage)
    }
    ContextCompat.startActivity(
        LocalContext.current,
        Intent.createChooser(webIntent, null),
        null
    )
}

@Composable
fun MealsAppBar(title: String, icon: ImageVector? = null, showProfile:Boolean = true, onBackArrowClicked: () -> Unit = {}) {
    TopAppBar(
        title = {
            Row {
                if (icon != null) {
                    Icon(imageVector = icon, contentDescription = "Logo",
                        tint = Color.Red.copy(alpha = 0.7f),
                        modifier = Modifier.clickable {
                            onBackArrowClicked.invoke()
                        })
                }
                val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels
                //Spacer(modifier = Modifier.width())
                Text(text = title,
                    modifier = Modifier.padding(start = screenWidth.dp.times(0.08f),
                        bottom = 19.dp)
                    ,
                    color = Color.Red.copy(alpha = 0.7f),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                if (showProfile) Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Icon(
                            Icons.Filled.AccountCircle, contentDescription = null,

                            )
                        Text("P.D",
                            modifier = Modifier.padding(2.dp),
                            style = MaterialTheme.typography.overline,
                            color = Color.Red)
                    }
                    Icon(imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow Right",
                        modifier = Modifier.clickable {})

                } else Surface() {}


            }
        }, backgroundColor = Color.Transparent, elevation = 0.dp)

}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text =  labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )
}