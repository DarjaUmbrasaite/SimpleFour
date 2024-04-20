package com.darjacreations.simplefour.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.darjacreations.simplefour.activities.MealActivity
import com.darjacreations.simplefour.databinding.FragmentHomeBinding
import com.darjacreations.simplefour.pojo.Meal
import com.darjacreations.simplefour.viewModel.HomeViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.darjacreations.simplefour.R
import com.darjacreations.simplefour.activities.CategoryMealsActivity
import com.darjacreations.simplefour.activities.MainActivity
import com.darjacreations.simplefour.activities.SignInActivity
import com.darjacreations.simplefour.adapters.CategoriesAdapter
import com.darjacreations.simplefour.adapters.MostPopularAdapter
import com.darjacreations.simplefour.pojo.MealsByCategory


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var randomMeal:Meal
    private lateinit var popularItemsAdapter: MostPopularAdapter
    private lateinit var categoriesAdapter:CategoriesAdapter
    private lateinit var viewModel: HomeViewModel

    companion object{
        const val MEAL_ID = "com.darjacreations.simplefour.idMeal"
        const val MEAL_NAME = "com.darjacreations.simplefour.nameMeal"
        const val MEAL_THUMB = "com.darjacreations.simplefour.thumbMeal"
        const val CATEGORY_NAME = "com.darjacreations.simplefour.fragments.categoryName"
        const val REQUEST_SIGNIN = "com.darjacreations.simplefour.fragments.request_signin"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        viewModel = (activity as MainActivity).viewModel




        popularItemsAdapter = MostPopularAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        binding = FragmentHomeBinding.inflate(inflater,container,false )

        binding.menuButton.setOnClickListener() {

        }

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularItemsRecyclerView()


        observerRandomMeal()
        onRandomMealClick()

        viewModel.getPopularItems()
        observePopularItemsLiveData()
        onPopularItemClick()

        prepareCategoriesRecyclerView()
        viewModel.getCategories()
        observeCategoriesLiveData()
        onCategoryClick()

        val imageView = view.findViewById<ImageView>(R.id.menu_button)
        imageView.setOnClickListener { v ->
            showPopupMenu(v)
        }

    }

    private fun showPopupMenu(v: View) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(R.menu.menu, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.itemOne -> {
                    // Handle menu item 1 action

                    val intent = Intent(context, SignInActivity::class.java)
                    intent.putExtra(HomeFragment.REQUEST_SIGNIN, true)
                    startActivity(intent)
                    true
                }
                R.id.itemTwo -> {
                    // Handle menu item 2 action
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    private fun onCategoryClick() {
        categoriesAdapter.onItemClick = { category ->
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(CATEGORY_NAME,category.strCategory)
            startActivity(intent)
        }
    }

    //this is where selections of categories/meals appear
    private fun prepareCategoriesRecyclerView() {
        categoriesAdapter = CategoriesAdapter()
        binding.recViewCategories.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }

    private fun observeCategoriesLiveData() {
        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner, Observer { categories->
                categoriesAdapter.setCategoryList(categories)

        })
    }

    private fun onPopularItemClick() {
        popularItemsAdapter.onItemsClick = { meal ->
            val intent = Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,meal.idMeal)
            intent.putExtra(MEAL_NAME,meal.strMeal)
            intent.putExtra(MEAL_THUMB,meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun preparePopularItemsRecyclerView() {
        binding.recViewMealsPopular.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemsAdapter
        }

    }

    private fun observePopularItemsLiveData() {
        viewModel.observePopularItemsLiveData().observe(viewLifecycleOwner,
            { mealList->
                popularItemsAdapter.setMeals(mealsList = mealList as ArrayList<MealsByCategory>)
            })


    }

    private fun onRandomMealClick() {
        binding.randomMealCard.setOnClickListener{
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observerRandomMeal() {
        viewModel.observeRandomMealLivedata().observe(viewLifecycleOwner,
            { meal ->
                Glide.with(this@HomeFragment)
                    .load(meal!!.strMealThumb)
                    .into(binding.imgRandomMeal)

                this.randomMeal = meal


            } )
    }


}