package com.darjacreations.simplefour.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.darjacreations.simplefour.pojo.Meal



@Database(entities = [Meal::class], version = 1) //whenever I change something in my database, remember to update the version number
@TypeConverters(MealTypeConverter::class)
//abstract, because I need to have an instance from the interface
abstract class MealDatabase : RoomDatabase() {
    abstract fun mealDao():MealDao

    companion object {
        @Volatile
        var INSTANCE: MealDatabase? = null

        @Synchronized //only one thread can have instance from this room database
        fun getInstance(context: Context):MealDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MealDatabase::class.java,
                    "meal.db"
                ) .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as MealDatabase
        }
    }



}