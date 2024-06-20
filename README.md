# Mi Recetario Android App

Este proyecto en una aplicación demo para consultar y buscar recetas, consta de 3 pantallas principales
* Home que contiene el listado principal de recetas
* Detail que muestra la receta a detalle (ingredientes, instrucciones, foto, etc)
* Map que muestra una ubicacion del origen de la receta, este puede ser nulo y no mostrar la opción.

## Stack

Esta aplicacion consta de un Front (android) y un back (aws)

### Front Stack

Esta aplicacion fue programada completamente en Kotlin con Jetpack Compose usando una arquitectura limpia de 3 capas (domain, data, presentation).
Entre las dependencias encontramos:
* Coil para descargar imagenes
* OkHttp y Retrofit para conectarse al backend
* Koin para inyeccion de dependencias
* Room como base de datos para hacer caché de los resultados con ayuda de PagingData y RemoteMediator 

### Beckend Stack

El backen solo tiene 1 endpoint para consultar el listado de recetas, esta implementado con componentes de AWS (Lambda, DynamoDB, ApiGateway)

## Herramientas adicionales

No soy diseñador, asi que me apoyé en otras herramientas como 
 * [IconScout](https://iconscout.com) para los iconos
 * [Looka](https://looka.com) para diseñar el logo principal
 * [Galileo](https://www.usegalileo.ai/explore) para diseñar las pantallas

