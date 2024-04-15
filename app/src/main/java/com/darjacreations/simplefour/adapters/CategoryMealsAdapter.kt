package com.darjacreations.simplefour.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darjacreations.simplefour.activities.MealActivity
import com.darjacreations.simplefour.databinding.MealItemBinding
import com.darjacreations.simplefour.fragments.HomeFragment
import com.darjacreations.simplefour.pojo.MealsByCategory

class CategoryMealsAdapter(private val context: Context) : RecyclerView.Adapter<CategoryMealsAdapter.CategoryMealsViewModel>() {
    private var mealsList = ArrayList<MealsByCategory>()

    fun setMealsList(mealsList:List<MealsByCategory>){
        this.mealsList = mealsList as ArrayList<MealsByCategory>
        notifyDataSetChanged()

    }

    inner class CategoryMealsViewModel(val binding:MealItemBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewModel {
        return CategoryMealsViewModel(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryMealsViewModel, position: Int) {
        Glide.with(holder.itemView).load(mealsList[position].strMealThumb).into(holder.binding.imgMeal)
        holder.binding.tvMealName.text = mealsList[position].strMeal
        holder.itemView.setOnClickListener(){
            val intent = Intent(context, MealActivity::class.java)
            intent.putExtra(HomeFragment.MEAL_ID, mealsList[position].idMeal)
            intent.putExtra(HomeFragment.MEAL_NAME, mealsList[position].strMeal)
            intent.putExtra(HomeFragment.MEAL_THUMB, mealsList[position].strMealThumb)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}