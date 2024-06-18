package me.kristianconk.mirecetario.util

import me.kristianconk.mirecetario.domain.model.Recipe

val simpleRecipe = Recipe(
    id = "12345",
    title = "Huevos pochados",
    description = "Huevos pochados o huevos poché",
    urlPhoto = null,
    ingredients = listOf("huevos (cantidad al gusto)", "vinagre blanco (15ml por cada litro)"),
    steps = listOf(
        "1 poner a calentar una olla PROFUNDA a fuego medio y añadir vinagre blanco",
        "2 partir el huevo y colocarlo en un recipiente para verificar consistencia",
        "3 esperar a que comience a burbujear el agua y con una cuchara dar vueltas al agua hasta formar un remolino",
        "4 dejar caer el huevo en el centro del remolino y esperar de 3 a 4 minutos antes de retirar",
        "5 repetir con cada huevo",
        "6 servir acompañado de guarnicion la gusto"
    ),
    notes = "Si no sabes si el huevo es fresco metelo en un vaso con agua, si se hunde es fresco si flota ya está pasado",
    location = null
)
