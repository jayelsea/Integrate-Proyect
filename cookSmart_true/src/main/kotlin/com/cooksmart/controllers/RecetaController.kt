package com.cooksmart.controllers

import com.cooksmart.models.Receta
import com.cooksmart.models.RecetaDetalle
import com.cooksmart.repositories.RecetaRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*

class RecetaController(private val repository: RecetaRepository) {

    // Función de extensión para Route
    fun Route.registerRoutes() {
        route("/recetas") {
            get {
                val recetas = repository.obtenerRecetasEspanolas()
                call.respond(recetas)
            }

            get("/buscar") {
                val nombre = call.request.queryParameters["nombre"]
                    ?: return@get call.respond(HttpStatusCode.BadRequest, "Falta parámetro 'nombre'")
                call.respond(repository.buscarRecetasPorNombre(nombre))
            }

            get("/{id}") {
                val id = call.parameters["id"]
                    ?: return@get call.respond(HttpStatusCode.BadRequest, "Falta ID de receta")
                repository.obtenerDetalleReceta(id)?.let { receta ->
                    call.respond(receta)
                } ?: call.respond(HttpStatusCode.NotFound, "Receta no encontrada")
            }
        }
    }
}
