package com.darjacreations.simplefour.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.darjacreations.simplefour.adapters.CategoryMealsAdapter
import com.darjacreations.simplefour.databinding.ActivityCategoryMealsBinding
import com.darjacreations.simplefour.fragments.SearchFragment
import com.darjacreations.simplefour.viewModel.SearchViewModel

class SearchResultsActivity : AppCompatActivity() {
    lateinit var binding : ActivityCategoryMealsBinding
    lateinit var searchViewModel: SearchViewModel
    lateinit var categoryMealsAdapter: CategoryMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()

        searchViewModel = ViewModelProvider( this)[SearchViewModel::class.java]

        searchViewModel.getMealsByIngredients(intent.getStringArrayListExtra(SearchFragment.SEARCH_LIST)!!)

        searchViewModel.observeMealsByCategoryListLiveData().observe( this) { mealsList ->
            if (mealsList.size == 0) {
                binding.tvCategoryCount.text = "No Recipes found that contain all these " +
                        "ingredients. Try again with fewer or different ingredient selections"
            } else {
                binding.tvCategoryCount.text = "Results: ${mealsList.size} Recipes"
            }
            categoryMealsAdapter.setMealsList(mealsList)
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