package com.darjacreations.simplefour.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darjacreations.simplefour.db.MealDatabase
import com.darjacreations.simplefour.pojo.Category
import com.darjacreations.simplefour.pojo.CategoryList
import com.darjacreations.simplefour.pojo.MealsByCategoryList
import com.darjacreations.simplefour.pojo.MealsByCategory
import com.darjacreations.simplefour.pojo.Meal
import com.darjacreations.simplefour.pojo.MealList
import com.darjacreations.simplefour.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private val mealDatabase: MealDatabase
):ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    private var randomCategoryItemsLiveData = MutableLiveData<MealsByCategoryList>()
    private var categoriesLiveData = MutableLiveData<List<Category>>()
    private var favouritesMealsLiveData = mealDatabase.mealDao().getAllMeals()

    //init block gets call when there is instance from the HomeViewModel,
    // view model still the same even after recreation
    init{
        getRandomMeal()
    }

    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body() !=null){
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d( "HomeFragment",t.message.toString())
            }
        })
    }


    fun getRandomCategory(){

        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                var categoryNames: List<String>? = null
                response.body()?.let { categoryList ->
                    categoryNames = categoryList.categories.map { it.strCategory }
                }
                var randomCategoryName = ""
                if (categoryNames != null) {
                    randomCategoryName = categoryNames!!.random()
                }

                RetrofitInstance.api.getMealsByCategory(randomCategoryName)
                    .enqueue(object : Callback<MealsByCategoryList> {
                        override fun onResponse(
                            call: Call<MealsByCategoryList>,
                            response: Response<MealsByCategoryList>
                        ) {
                            if (response.body() != null) {
                                val mealsByCategoryList = response.body()!!
                                mealsByCategoryList.categoryName = randomCategoryName
                                randomCategoryItemsLiveData.value = mealsByCategoryList
                            }
                        }
                        override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                            Log.d("HomeFragment", t.message.toString())
                        }
                    })
            }
            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.e( "HomeViewModel",t.message.toString())
            }
         })
    }

    fun getCategories(){
       RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList>{
           override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                response.body()?.let { categoryList ->
                    categoriesLiveData.postValue(categoryList.categories)
                }
           }

           override fun onFailure(call: Call<CategoryList>, t: Throwable) {
               Log.e( "HomeViewModel",t.message.toString())
           }
       })
    }

    fun deleteMeal(meal:Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().delete(meal)
        }
    }

    fun insertMeal(meal:Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().upsert(meal)
        }
    }

    fun observeRandomMealLivedata(): LiveData<Meal> {
        return randomMealLiveData
    }

    fun observeRandomCategoryItemsLiveData():LiveData<MealsByCategoryList> {
        return randomCategoryItemsLiveData

    }

    fun observeCategoriesLiveData():LiveData<List<Category>>{

        return categoriesLiveData

    }

    fun observeFavouritesMealsLiveData():LiveData<List<Meal>>{
        return favouritesMealsLiveData
    }




}