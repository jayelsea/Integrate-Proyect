package com.cooksmart.repositories

import com.cooksmart.models.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class RecetaRepository(private val httpClient: HttpClient) {

    private val baseUrl = "https://www.themealdb.com/api/json/v1/1"

    suspend fun obtenerRecetasEspanolas(): List<Receta> {
        return try {
            val response: MealDbResponse = httpClient.get("$baseUrl/filter.php?a=Spanish").body()
            response.meals?.map { meal ->
                Receta(
                    id = meal.idMeal,
                    nombre = meal.strMeal,
                    imagen = meal.strMealThumb,
                    categoria = meal.strCategory,
                    area = meal.strArea
                )
            } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun buscarRecetasPorNombre(nombre: String): List<Receta> {
        return try {
            val response: MealDbResponse = httpClient.get("$baseUrl/search.php?s=$nombre").body()
            response.meals?.map { meal ->
                Receta(
                    id = meal.idMeal,
                    nombre = meal.strMeal,
                    imagen = meal.strMealThumb,
                    categoria = meal.strCategory,
                    area = meal.strArea
                )
            } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun obtenerDetalleReceta(id: String): RecetaDetalle? {
        return try {
            val response: MealDbDetailResponse = httpClient.get("$baseUrl/lookup.php?i=$id").body()
            response.meals?.firstOrNull()?.let { mealDetail ->
                RecetaDetalle(
                    id = mealDetail.idMeal,
                    nombre = mealDetail.strMeal,
                    imagen = mealDetail.strMealThumb,
                    instrucciones = mealDetail.strInstructions,
                    ingredientes = extraerIngredientes(mealDetail),
                    area = mealDetail.strArea,
                    categoria = mealDetail.strCategory
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun extraerIngredientes(mealDetail: MealDetail): List<Ingrediente> {
        val ingredientes = mutableListOf<Ingrediente>()

        // Lista de pares ingrediente-medida
        val ingredientesPares = listOf(
            mealDetail.strIngredient1 to mealDetail.strMeasure1,
            mealDetail.strIngredient2 to mealDetail.strMeasure2,
            mealDetail.strIngredient3 to mealDetail.strMeasure3,
            mealDetail.strIngredient4 to mealDetail.strMeasure4,
            mealDetail.strIngredient5 to mealDetail.strMeasure5,
            mealDetail.strIngredient6 to mealDetail.strMeasure6,
            mealDetail.strIngredient7 to mealDetail.strMeasure7,
            mealDetail.strIngredient8 to mealDetail.strMeasure8,
            mealDetail.strIngredient9 to mealDetail.strMeasure9,
            mealDetail.strIngredient10 to mealDetail.strMeasure10,
            mealDetail.strIngredient11 to mealDetail.strMeasure11,
            mealDetail.strIngredient12 to mealDetail.strMeasure12,
            mealDetail.strIngredient13 to mealDetail.strMeasure13,
            mealDetail.strIngredient14 to mealDetail.strMeasure14,
            mealDetail.strIngredient15 to mealDetail.strMeasure15,
            mealDetail.strIngredient16 to mealDetail.strMeasure16,
            mealDetail.strIngredient17 to mealDetail.strMeasure17,
            mealDetail.strIngredient18 to mealDetail.strMeasure18,
            mealDetail.strIngredient19 to mealDetail.strMeasure19,
            mealDetail.strIngredient20 to mealDetail.strMeasure20
        )

        ingredientesPares.forEach { (ingrediente, medida) ->
            if (!ingrediente.isNullOrBlank()) {
                ingredientes.add(
                    Ingrediente(
                        nombre = ingrediente.trim(),
                        medida = medida?.trim() ?: ""
                    )
                )
            }
        }

        return ingredientes
    }
}