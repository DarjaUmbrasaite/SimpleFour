package com.darjacreations.simplefour.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darjacreations.simplefour.db.MealDatabase

class MealViewModelProduce(
    private val mealDatabase: MealDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return MealViewModel(mealDatabase) as T
        }



}
