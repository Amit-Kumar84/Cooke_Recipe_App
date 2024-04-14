package com.rajput.cookingrecipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rajput.cookingrecipe.data.RecipeData
import com.rajput.cookingrecipe.model.RecipeBook.recipes
import com.rajput.cookingrecipe.ui.theme.CookingRecipeTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookingRecipeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeApp()

                }
            }
        }
    }
}

@Composable
fun RecipeApp(
){
    Scaffold (modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() }
    ) {it->
        LazyColumn(contentPadding = it) {
            items(recipes){
                RecipeListItem(
                    modifier = Modifier,
                    recipe = it,


                    )




            }


        }
    }
}


@Composable
fun RecipeListItem(recipe: RecipeData,
                   modifier: Modifier = Modifier,
) {
    val isExpanded = remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(
        targetValue = if (isExpanded.value) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer, label = ""
    )
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),

        modifier = modifier
            .padding(16.dp)

            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium

                )

            )


            .clickable { isExpanded.value = !isExpanded.value }


    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()

                .background(color = color)
        ){

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)


            ) {
                RecipeInformation(
                    recipe.dayRes,
                    recipe.nameRes,
                )
                RecipePic(
                    recipe.imageRes,
                    modifier = Modifier.padding(top = 8.dp)
                )
                AnimatedVisibility(
                    visible = isExpanded.value,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    RecipeAbout(
                        recipe.descriptionRes,
                        modifier = Modifier.padding(
                            start = (16.dp),
                            top = (16.dp),
                            bottom = (16.dp),
                            end = (16.dp)

                        )
                    )


                }


            }
        }


    }

}



@Composable
fun RecipeInformation(
    @StringRes day: Int,
    @StringRes recipeName: Int,

    modifier: Modifier = Modifier
){
    Column (modifier = modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)) {
        Text(
            text = stringResource(id = day),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(id = recipeName),
            style = MaterialTheme.typography.displayMedium)

    }
}
@Composable
fun RecipePic(
    @DrawableRes recipePic: Int,
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(id = recipePic),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .height(194.dp),
        contentScale = ContentScale.Crop
    )

}

@Composable
fun RecipeAbout(
    @StringRes recipeInformation: Int,
    modifier: Modifier = Modifier
){
    Column( modifier = modifier) {
        Text(text = stringResource(id = recipeInformation),
            style = MaterialTheme.typography.bodyMedium)

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier){
    TopAppBar(title =
    {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayLarge
        )
    },
        modifier = modifier
    )

}
@Preview
@Composable
fun PreviewRecipeCard() {
    CookingRecipeTheme {
        RecipeApp()
    }
}
