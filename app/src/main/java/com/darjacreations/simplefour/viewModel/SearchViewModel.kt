package com.darjacreations.simplefour.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darjacreations.simplefour.pojo.Category
import com.darjacreations.simplefour.pojo.CategoryList
import com.darjacreations.simplefour.pojo.Ingredient
import com.darjacreations.simplefour.pojo.IngredientList
import com.darjacreations.simplefour.pojo.MealsByCategoryList
import com.darjacreations.simplefour.pojo.MealsByCategory
import com.darjacreations.simplefour.pojo.Meal
import com.darjacreations.simplefour.pojo.MealList
import com.darjacreations.simplefour.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel():ViewModel() {

    private var ingredientListLiveData = MutableLiveData<List<Ingredient>>()


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


    fun observeIngredientListLiveData():LiveData<List<Ingredient>> {
        return ingredientListLiveData
    }

}