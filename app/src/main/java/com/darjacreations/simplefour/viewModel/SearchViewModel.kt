package com.darjacreations.simplefour.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darjacreations.simplefour.pojo.Ingredient
import com.darjacreations.simplefour.pojo.IngredientList
import com.darjacreations.simplefour.pojo.MealsByCategoryList
import com.darjacreations.simplefour.pojo.MealsByCategory

import com.darjacreations.simplefour.retrofit.RetrofitInstance
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel():ViewModel() {

    private var ingredientListLiveData = MutableLiveData<List<Ingredient>>()
    private var mealsByCategoryListLiveData = MutableLiveData<List<MealsByCategory>>()

    fun getIngredients(){
        RetrofitInstance.api.getIngredients().enqueue(object : Callback<IngredientList> {
            override fun onResponse(call: Call<IngredientList>, response: Response<IngredientList>) {
                if(response.body() !=null){
                    ingredientListLiveData.value = response.body()!!.ingredients
                }else{
                    return
                }
            }
            override fun onFailure(call: Call<IngredientList>, t: Throwable) {
                Log.e( "SearchViewModel",t.message.toString())
            }
        })
    }

    // uses coroutines for multiple calls
    fun getMealsByIngredients(ingredients: List<String>) {
        viewModelScope.launch {
            // one async operation per ingredient
            val mealsLists: List<Deferred<Response<MealsByCategoryList>?>> = ingredients.map { ingredient ->
                async(Dispatchers.IO) {
                    try {
                        RetrofitInstance.api.getMealsByIngredient(ingredient).await()
                    } catch (e: Exception) {
                        Log.e("MealViewModel", "Error fetching meals for ingredient $ingredient", e)
                        null
                    }
                }
            }

            val results = mealsLists.awaitAll()

            // keep only successful responses and get meals
            val successfulResults = results.filter { it?.isSuccessful ?: false}.mapNotNull { it?.body()?.meals }

            // Find common meal IDs across lists
            val commonMealIds = findCommonMealIds(successfulResults)
            val commonMeals = successfulResults.flatten().distinct().filter { it.idMeal in commonMealIds }

            mealsByCategoryListLiveData.postValue(commonMeals)
        }
    }

    private fun findCommonMealIds(mealsLists: List<List<MealsByCategory>>): Set<String> {
        // finds mealids that are common to all lists
        val commonlist =  mealsLists.map { it.map { meal -> meal.idMeal }.toSet() }
            .reduce { acc, set -> acc.intersect(set) }
        return commonlist
    }
    fun observeMealsByCategoryListLiveData():LiveData<List<MealsByCategory>> {
        return mealsByCategoryListLiveData
    }
    fun observeIngredientListLiveData():LiveData<List<Ingredient>> {
        return ingredientListLiveData
    }

}