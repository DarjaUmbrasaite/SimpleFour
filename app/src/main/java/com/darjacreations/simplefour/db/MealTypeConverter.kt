package com.darjacreations.simplefour.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters


@TypeConverters
class MealTypeConverter {

    @TypeConverter
    fun fromAnyToString(attribute:Any?) : String{//this function will be used when it will want to insert inside the table
        if(attribute == null)
            return ""
        return attribute as String
    }

    @TypeConverter
    fun fromStringToAny(attribute: String?):Any{// function to retrieve data from the database
        if(attribute == null)
            return ""
        return attribute
    }
}