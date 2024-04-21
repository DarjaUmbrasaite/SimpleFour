package com.darjacreations.simplefour.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.darjacreations.simplefour.fragments.HomeFragment
import com.darjacreations.simplefour.R
import com.darjacreations.simplefour.databinding.ActivityMealBinding
import com.darjacreations.simplefour.db.MealDatabase
import com.darjacreations.simplefour.pojo.Meal
import com.darjacreations.simplefour.viewModel.MealViewModel
import com.darjacreations.simplefour.viewModel.MealViewModelProduce


class MealActivity : AppCompatActivity() {
    private lateinit var mealId:String
    private lateinit var mealName:String
    private lateinit var mealThumb:String
    private lateinit var binding:ActivityMealBinding
    private lateinit var youtubeLink:String
    private lateinit var mealMvvm:MealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instance from the database (view model)
        val mealDatabase = MealDatabase.getInstance( this)
        val viewModelProduce = MealViewModelProduce(mealDatabase)
        mealMvvm = ViewModelProvider( this, viewModelProduce)[MealViewModel::class.java]


        getMealInformationFromIntent()

        setInformationInViews()

        loadingCase()
        mealMvvm.getMealDetail(mealId)
        observerMealDetailsLiveData()

        //click listeners for buttons
        onYouTubeImageClick()
        onFavouritesClick()
        onIngredientsClick()
        onReceipeStepsClick()
    }

    private fun onFavouritesClick() {
        binding.btnAddToFav.setOnClickListener {
            mealToRecord?.let {
                mealMvvm.insertMeal(it)
                Toast.makeText(this, "Meal saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onIngredientsClick() {
        binding.btnIngredients.setOnClickListener {
            mealToRecord?.let {
                binding.tvInstructions.text = getString(R.string.ingredients)
                binding.tvInstructionsSt.text = mealToRecord!!.createIngredientsList()
            }
        }
    }

    private fun onReceipeStepsClick() {
        binding.btnInstructions.setOnClickListener {
            mealToRecord?.let {
                binding.tvInstructions.text = getString(R.string.recipe_steps)
                binding.tvInstructionsSt.text = mealToRecord!!.strInstructions
            }
        }
    }

    //function that allows to watch youtube video.2
    private fun onYouTubeImageClick() {
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }

    }

    private var mealToRecord:Meal?=null
    //that's where i get response case api. Function listens to the live data,
    // live data changed and response is received
    private fun observerMealDetailsLiveData() {
        mealMvvm.observerMealDetailsLiveData().observe(this
        ) { t ->
            onResponseCase()
            val meal = t
            mealToRecord = meal
            binding.tvInstructionsSt.text = meal.strInstructions


            youtubeLink = meal?.strYoutube ?: ""
        }
    }

    private fun setInformationInViews() {//setting meal image inside image view
        Glide.with(applicationContext)
           .load(mealThumb)
           .into(binding.imgMealDetail)

    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!

    }

    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnAddToFav.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
//        binding.tvCategory.visibility = View.INVISIBLE
//        binding.tvArea.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE

    }
    private fun onResponseCase(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnAddToFav.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE

    }
}