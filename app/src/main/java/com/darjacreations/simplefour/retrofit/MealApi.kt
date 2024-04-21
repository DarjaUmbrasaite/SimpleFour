package com.darjacreations.simplefour.retrofit

import com.darjacreations.simplefour.pojo.CategoryList
import com.darjacreations.simplefour.pojo.IngredientList
import com.darjacreations.simplefour.pojo.MealsByCategoryList
import com.darjacreations.simplefour.pojo.MealList
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("list.php?i=list")
    fun getIngredients():Call<IngredientList>

    @GET( "random.php")
    fun getRandomMeal():Call<MealList>


    @GET( "lookup.php")
    fun getMealDetails(@Query("i") id:String) : Call<MealList>

    @GET( "categories.php")
    fun getCategories() : Call<CategoryList>
    //without question mark i can have any number or queries inside this function
    @GET( "filter.php")
    fun getMealsByCategory(@Query( "c") categoryName: String) :Call<MealsByCategoryList>

    // Using a coroutine instead of callback
    @GET("filter.php")
    fun getMealsByIngredient(@Query("i") categoryName: String) : Deferred<Response<MealsByCategoryList>>

}