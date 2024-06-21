# Mi Recetario Android App

Este proyecto en una aplicación demo para consultar y buscar recetas, consta de 3 pantallas principales
* Home que contiene el listado principal de recetas y un buscador en la parte superior
* Detail que muestra la receta a detalle (ingredientes, instrucciones, foto, etc)
* Map que muestra una ubicacion del origen de la receta, este puede ser nulo y no mostrar la opción.

![splash](/doc/images/splash.png)
![home](/doc/images/home.png)
![search](/doc/images/search.png)
![detail](/doc/images/detail.png)
![map](/doc/images/map.png)

## Stack

Esta aplicación consta de un Front (android) y un back (aws)

### Front Stack

Esta aplicacion fue programada completamente en Kotlin con Jetpack Compose usando una arquitectura limpia de 3 capas (domain, data, presentation).
Se utilizo el IDE más actualizado (Android Studio Jellyfish | 2023.3.1 Patch 1)
Decidi usar minSDK 26 (8 Oreo) ya que se tiene una cobertura de 95% de los dispositivos actuales lo cual es bastante bueno y se evitan algunas complicaciones por dar soporte a versiones más viejas
Un 90% del codigo es de mi completa autoria, sin embargo para algunos aspectos recurri a inspiracion de internet
[Detectar el cambio de internet](https://medium.com/scalereal/observing-live-connectivity-status-in-jetpack-compose-way-f849ce8431c7)
[Uso del paginador de resultados](https://github.com/AliAsadi/Android-Clean-Architecture/blob/master/data/src/main/kotlin/com/aliasadi/data/repository/movie/MovieRemoteMediator.kt)

Algunas dependencias resaltables del proyecto son:
* Coil para descargar imagenes
* Lottie para animaciones
* OkHttp y Retrofit para conectarse al backend
* Koin para inyeccion de dependencias
* Room como base de datos para hacer caché de los resultados con ayuda de PagingData y RemoteMediator


### Beckend Stack

El backen solo tiene 1 endpoint para consultar el listado de recetas, esta implementado con componentes de AWS (Lambda, DynamoDB, ApiGateway)

![diagrama](/doc/images/recetas-aws.jpg)

## Herramientas adicionales

No soy diseñador, asi que me apoyé en otras herramientas como 
 * [IconScout](https://iconscout.com) para los iconos
 * [Looka](https://looka.com) para diseñar el logo principal
 * [Galileo](https://www.usegalileo.ai/explore) para diseñar las pantallas

## Roadmap

El estado actual de la aplicación es solo un demo sencillo para demostrar mi capacidades como programador en un tiempo record de 7 días.
Sin embargo con más tiempo se puede robustecer con más fucionalidades como:
- añadir tags o categorías a las recetas y asi filtrar por ejemplo solo recetas de una region o de un ingrediente (pollo, comida china, etc) -> Cambios en back y front
- tener una vista de mapa desde la pantalla pricipal y ver las recetas cercanas a tu ubicacion -> Solictar permisos de ubicación al usuario
- tener la posibilidad de guardar recetas en favoritos y verlas en otra seccion de la app -> Crear otra tabla en room donde se gaurden los favoritos ademas de su correspondiente Dao para guardar leer y borrar

## known issues

Es posible experimentar fallos cuando no se cuenta con una buena conexcion a internet, la aplicación solo avisa del error, pero no tiene un sistema robusto de recuperación de errores