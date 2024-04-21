package com.darjacreations.simplefour.pojo

data class MealsByCategoryList(
    var categoryName: String,
    val meals: List<MealsByCategory>
)