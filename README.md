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

#### Unit Testing

Si bien en principio se debería tener una prueba unitaria por cada clase en el código, la realidad es que solo vale la pena meter pruebas en clases relacionadas a funcionalidad o negocio, es decir clases de validacion de formularios, utilidades de formatos/fechas, reglas de negocio, como permisos de usuario.
Desafortunadamente el proyecto solo tiene un par de pruebas unitarias que son significativas. El resto no se puede probar por la dificultar de simular un comportamiento o por la falta de valor de probar casos de uso que no tienen logica y redirigen la peticion/resultado al repositorio.

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
## License
<a href="https://github.com/newkristian">Developer Sr</a> © 2025 by <a href="https://creativecommons.org">Christian Soto</a> is licensed under <a href="https://creativecommons.org/licenses/by-nc/4.0/">CC BY-NC 4.0</a><img src="https://mirrors.creativecommons.org/presskit/icons/cc.svg" style="max-width: 1em;max-height:1em;margin-left: .2em;"><img src="https://mirrors.creativecommons.org/presskit/icons/by.svg" style="max-width: 1em;max-height:1em;margin-left: .2em;"><img src="https://mirrors.creativecommons.org/presskit/icons/nc.svg" style="max-width: 1em;max-height:1em;margin-left: .2em;">
