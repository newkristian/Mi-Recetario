package me.kristianconk.mirecetario.mocks

import me.kristianconk.mirecetario.data.model.RecipeDTO
import java.util.Date

val recipeDTOMock = RecipeDTO(
    id = "12345",
    title = "Huevos pochados",
    description = "Huevos pochados o huevos poché",
    urlPhoto = "https://www.elespectador.com/resizer/6nuhDuY2YuVPNSx6YDKRx6OyqRY=/920x613/filters:quality(60):format(jpeg)/www.elespectador.com/resizer/RJ3jMHJmXHfHjRJ8Y3uwPjn3D6I=/arc-anglerfish-arc2-prod-elespectador/public/4D6SRFYTVFBUZGFH2WBVJFZESY.jpg",
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
    creationDate = "2024-06-20T14:09:21",
    latitude = 19.3909832,
    longitude = -99.3084181
)