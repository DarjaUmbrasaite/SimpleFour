package com.darjacreations.simplefour.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "mealInformation")
data class Meal(
    val dateModified: Any?,
    @PrimaryKey()
    val idMeal: String,
    val strArea: String?,
    val strCategory: String?,
    val strCreativeCommonsConfirmed: Any?,
    val strDrinkAlternate: Any?,
    val strImageSource: Any?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: String?,
    val strIngredient17: String?,
    val strIngredient18: String?,
    val strIngredient19: String?,
    val strIngredient20: String?,
    val strInstructions: String?,
    val strMeal: String?,
    val strMealThumb: String?,
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure16: String?,
    val strMeasure17: String?,
    val strMeasure18: String?,
    val strMeasure19: String?,
    val strMeasure20: String?,
    val strSource: String?,
    val strTags: Any?,
    val strYoutube: String?
){

fun createIngredientsList(): String {
    val ingredients = mutableListOf<String>()

    // Faster than dynamically accessing in a loop
    this.strIngredient1?.let { ingredient ->
        this.strMeasure1?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient2?.let { ingredient ->
        this.strMeasure2?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient3?.let { ingredient ->
        this.strMeasure3?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient4?.let { ingredient ->
        this.strMeasure4?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient5?.let { ingredient ->
        this.strMeasure5?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient6?.let { ingredient ->
        this.strMeasure6?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient7?.let { ingredient ->
        this.strMeasure7?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient8?.let { ingredient ->
        this.strMeasure8?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient9?.let { ingredient ->
        this.strMeasure9?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient10?.let { ingredient ->
        this.strMeasure10?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient11?.let { ingredient ->
        this.strMeasure11?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient12?.let { ingredient ->
        this.strMeasure12?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient13?.let { ingredient ->
        this.strMeasure13?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient14?.let { ingredient ->
        this.strMeasure14?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient15?.let { ingredient ->
        this.strMeasure15?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient16?.let { ingredient ->
        this.strMeasure16?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient17?.let { ingredient ->
        this.strMeasure17?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient18?.let { ingredient ->
        this.strMeasure18?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient19?.let { ingredient ->
        this.strMeasure19?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }
    this.strIngredient20?.let { ingredient ->
        this.strMeasure20?.let { measure ->
            if (ingredient.isNotEmpty()){
                ingredients.add("$ingredient: $measure")}
        }
    }


    return ingredients.joinToString("\n")
}}