package com.darjacreations.simplefour.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darjacreations.simplefour.databinding.PopularItemsBinding
import com.darjacreations.simplefour.pojo.MealsByCategory

class MostPopularAdapter(): RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>() {
    lateinit var onItemsClick:((MealsByCategory) -> Unit)
    private var mealsList = ArrayList<MealsByCategory>()

    fun setMeals(mealsList:ArrayList<MealsByCategory>){
        this.mealsList = mealsList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imgPopularMealItem)

        holder .itemView.setOnClickListener {
            onItemsClick.invoke(mealsList[position])
        }
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    class PopularMealViewHolder(var binding:PopularItemsBinding):RecyclerView.ViewHolder(binding.root)
}