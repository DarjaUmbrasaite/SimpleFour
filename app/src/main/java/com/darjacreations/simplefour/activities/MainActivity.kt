package com.darjacreations.simplefour.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.darjacreations.simplefour.R
import com.darjacreations.simplefour.db.MealDatabase
import com.darjacreations.simplefour.viewModel.HomeViewModel
import com.darjacreations.simplefour.viewModel.HomeViewModelProduce
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val viewModel: HomeViewModel by lazy {
        val mealDatabase = MealDatabase.getInstance( this)
        val homeViewModelProviderProduce =  HomeViewModelProduce(mealDatabase)
        ViewModelProvider( this, homeViewModelProviderProduce)[HomeViewModel::class.java]


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.btm_nav)
        val navController = Navigation.findNavController(this, R.id.host_fragment)

        NavigationUI.setupWithNavController(bottomNavigation,navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
}