package com.darjacreations.simplefour.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darjacreations.simplefour.pojo.MealsByCategory
import com.darjacreations.simplefour.pojo.MealsByCategoryList
import com.darjacreations.simplefour.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel : ViewModel() {
    val mealsLiveData = MutableLiveData<MealsByCategoryList>()

    fun getMealsByCategory(categoryName:String){
        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object : Callback<MealsByCategoryList>{
            override fun onResponse(
                call: Call<MealsByCategoryList>,
                response: Response<MealsByCategoryList>
            ) {

                if (response.body() != null) {
                    val mealsList = response.body()!!
                    mealsList.categoryName = categoryName
                    mealsLiveData.value = mealsList
                }

            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.e( "CategoryMealsViewModel",t.message.toString())
            }
        })
    }

    fun observeMealsLiveData(): LiveData<MealsByCategoryList> {
        return mealsLiveData
    }
}