package com.cooksmart.models

import kotlinx.serialization.Serializable

@Serializable
data class Receta(
    val id: String,
    val nombre: String,
    val imagen: String,
    val categoria: String? = null,
    val area: String? = null
)

@Serializable
data class RecetaDetalle(
    val id: String,
    val nombre: String,
    val imagen: String,
    val instrucciones: String,
    val ingredientes: List<Ingrediente>,
    val area: String,
    val categoria: String
)

@Serializable
data class Ingrediente(
    val nombre: String,
    val medida: String
)


@Serializable
data class MealDbResponse(val meals: List<Meal>? = null)

@Serializable
data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strCategory: String? = null,
    val strArea: String? = null
)

@Serializable
data class MealDbDetailResponse(val meals: List<MealDetail>? = null)

@Serializable
data class MealDetail(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strInstructions: String,
    val strArea: String,
    val strCategory: String,
    // Ingredientes del 1 al 20
    val strIngredient1: String? = null,
    val strMeasure1: String? = null,
    val strIngredient2: String? = null,
    val strMeasure2: String? = null,
    val strIngredient3: String? = null,
    val strMeasure3: String? = null,
    val strIngredient4: String? = null,
    val strMeasure4: String? = null,
    val strIngredient5: String? = null,
    val strMeasure5: String? = null,
    val strIngredient6: String? = null,
    val strMeasure6: String? = null,
    val strIngredient7: String? = null,
    val strMeasure7: String? = null,
    val strIngredient8: String? = null,
    val strMeasure8: String? = null,
    val strIngredient9: String? = null,
    val strMeasure9: String? = null,
    val strIngredient10: String? = null,
    val strMeasure10: String? = null,
    val strIngredient11: String? = null,
    val strMeasure11: String? = null,
    val strIngredient12: String? = null,
    val strMeasure12: String? = null,
    val strIngredient13: String? = null,
    val strMeasure13: String? = null,
    val strIngredient14: String? = null,
    val strMeasure14: String? = null,
    val strIngredient15: String? = null,
    val strMeasure15: String? = null,
    val strIngredient16: String? = null,
    val strMeasure16: String? = null,
    val strIngredient17: String? = null,
    val strMeasure17: String? = null,
    val strIngredient18: String? = null,
    val strMeasure18: String? = null,
    val strIngredient19: String? = null,
    val strMeasure19: String? = null,
    val strIngredient20: String? = null,
    val strMeasure20: String? = null
)