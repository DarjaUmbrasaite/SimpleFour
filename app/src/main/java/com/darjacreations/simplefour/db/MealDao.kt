package com.darjacreations.simplefour.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.darjacreations.simplefour.pojo.Meal

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)//when adding a new meal that already exist, instead of inserting, it will update it
    suspend fun  upsert(meal: Meal)

    @Delete
    suspend fun delete(meal:Meal)

    @Query( "SELECT * FROM mealInformation")//order by - can add, to select how i want to order this query
    fun getAllMeals(): LiveData<List<Meal>>

}