package com.darjacreations.simplefour.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.darjacreations.simplefour.adapters.CategoryMealsAdapter
import com.darjacreations.simplefour.databinding.ActivityCategoryMealsBinding
import com.darjacreations.simplefour.fragments.HomeFragment
import com.darjacreations.simplefour.viewModel.CategoryMealsViewModel

class CategoryMealsActivity : AppCompatActivity() {
    lateinit var binding : ActivityCategoryMealsBinding
    lateinit var categoryMealsViewModel:CategoryMealsViewModel
    lateinit var categoryMealsAdapter: CategoryMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()

        categoryMealsViewModel = ViewModelProvider( this)[CategoryMealsViewModel::class.java]

        categoryMealsViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)

        categoryMealsViewModel.observeMealsLiveData().observe( this) { mealsList ->
            if (mealsList.meals.size == 0) {
                binding.tvCategoryCount.text = "No Recipes found for this category"
            } else {
                binding.tvCategoryCount.text = "Results: ${mealsList.meals.size} ${mealsList.categoryName} Recipes"
            }
            categoryMealsAdapter.setMealsList(mealsList.meals)

        }
    }

    private fun prepareRecyclerView() {
        categoryMealsAdapter = CategoryMealsAdapter(this)
        binding.rvMeals.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
                adapter = categoryMealsAdapter

        }
    }

}