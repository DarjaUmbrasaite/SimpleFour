package com.darjacreations.simplefour.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darjacreations.simplefour.activities.MealActivity
import com.darjacreations.simplefour.databinding.MealItemBinding
import com.darjacreations.simplefour.fragments.FavouritesFragment
import com.darjacreations.simplefour.fragments.HomeFragment
import com.darjacreations.simplefour.pojo.Meal

class FavouritesMealsAdapter(private val context: Context) : RecyclerView.Adapter<FavouritesMealsAdapter.FavouritesMealsAdapterViewHolder>() {

    inner class FavouritesMealsAdapterViewHolder(val binding:MealItemBinding) : RecyclerView.ViewHolder(binding.root)


    //class in recycler view that improves its performance, refreshes the items that only get changed
    private val diffUtil = object : DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }


    val differ = AsyncListDiffer( this, diffUtil)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouritesMealsAdapterViewHolder {
        return FavouritesMealsAdapterViewHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context),parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouritesMealsAdapterViewHolder, position: Int) {
        val meal = differ.currentList[position]
        Glide.with(holder.itemView).load(meal.strMealThumb).into(holder.binding.imgMeal)
        holder.binding.tvMealName.text = meal.strMeal
        holder.itemView.setOnClickListener(){
            val intent = Intent(context, MealActivity::class.java)
            intent.putExtra(HomeFragment.MEAL_ID, differ.currentList[position].idMeal)
            intent.putExtra(HomeFragment.MEAL_NAME, differ.currentList[position].strMeal)
            intent.putExtra(HomeFragment.MEAL_THUMB, differ.currentList[position].strMealThumb)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}