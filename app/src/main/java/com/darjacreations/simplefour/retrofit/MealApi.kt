package com.darjacreations.simplefour.retrofit

import com.darjacreations.simplefour.pojo.CategoryList
import com.darjacreations.simplefour.pojo.IngredientList
import com.darjacreations.simplefour.pojo.MealsByCategoryList
import com.darjacreations.simplefour.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("list.php?i=list")
    fun getIngredients():Call<IngredientList>

    @GET( "random.php")
    fun getRandomMeal():Call<MealList>

    @GET( "lookup.php?")
    fun getMealDetails(@Query("i") id:String) : Call<MealList>
    //with question marl i can have one query inside this function
    @GET("filter.php?")
    fun getPopularItems(@Query("c") categoryName:String) : Call<MealsByCategoryList>

    @GET( "categories.php")
    fun getCategories() : Call<CategoryList>
    //without question mark i can have any number or queries inside this function
    @GET( "filter.php")
    fun getMealsByCategory(@Query( "c") categoryName: String) :Call<MealsByCategoryList>
}