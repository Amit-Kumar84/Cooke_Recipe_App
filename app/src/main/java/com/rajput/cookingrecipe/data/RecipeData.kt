package com.rajput.cookingrecipe.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class RecipeData (
    @StringRes val dayRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int

)