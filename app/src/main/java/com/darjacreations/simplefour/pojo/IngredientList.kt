package com.darjacreations.simplefour.pojo

import com.google.gson.annotations.SerializedName

data class IngredientList(
    @SerializedName("meals") val ingredients: List<Ingredient>
)