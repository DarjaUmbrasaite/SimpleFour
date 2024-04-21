package com.darjacreations.simplefour.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darjacreations.simplefour.databinding.RandomCategoryItemsBinding
import com.darjacreations.simplefour.pojo.MealsByCategory

class RandomCategoryAdapter(): RecyclerView.Adapter<RandomCategoryAdapter.RandomCategoryMealViewHolder>() {
    lateinit var onItemsClick:((MealsByCategory) -> Unit)
    private var mealsList = ArrayList<MealsByCategory>()

    fun setMeals(mealsList:ArrayList<MealsByCategory>){
        this.mealsList = mealsList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomCategoryMealViewHolder {
        return RandomCategoryMealViewHolder(RandomCategoryItemsBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: RandomCategoryMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imgRandomCategoryMealItem)
        holder.binding.tvRandomCategoryItem.text = mealsList[position].strMeal
        holder .itemView.setOnClickListener {
            onItemsClick.invoke(mealsList[position])
        }
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    class RandomCategoryMealViewHolder(var binding:RandomCategoryItemsBinding):RecyclerView.ViewHolder(binding.root)
}