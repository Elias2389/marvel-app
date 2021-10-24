
* * *

# Marvel App Documentation

**Especificación del servicio:** API usada *https://developer.marvel.com/*

**Casos de uso:** 
* El usuario al ingresar en la aplicación deberá visualizar listado de los personajes de Marvel.
* El usuario deberá visualizar la descripción de cada uno de los personajes.

* * *

# Implementación
Se ha desarrollado una aplicaón que permite al usuario realizar las siguientes acciones:
* Listar los personajes de marvel provenientes desde la API, cuenta con paginado mediante offset y limit que se encuentra limitado con un maximo de 60 personajes para efectos practicos y asi evitar cargar cientos de páginas; esto puede ser modificado.
* El listado de los personajes cuenta con cache, este permite tener experiencia offline.
* Seleccionar un personaje en específico y observar su detalle en una nueva pantalla.

### Generales
* Las requests son interceptadas a traves de una clase Interceptor que permite agregar los query params necesarios para poder consumir el servicio de marvel.
* Las Keys se encuentran en el archivo gradle.properties.

### Tecnologías y herramientas
Algunas de las tecnologías utilizadas en la construcción del proyecto:
* Kotlin
* Viewmodel
* LiveData
* Room
* Dagger Hilt
* Coroutines
* Junit
* Mockk
* Glide
* Retrofit
* Gson

* * *
## Estructura

### Arquitectura
La aplicación se encuentra separada por capas con MVVM:

**Capa de presentacion:** compuesta por Views(Activities, Fragments) y ViewModel.

**Capa de dominio:** compuesta los Use Case.

**Capa de datos:** compuesta por los Repositories.

**Composición de los packages de forma grafica:**
````
|-- common
|   |-- components
|   |-- connectionchecker
|   |-- listener
|   |-- reponse
|   `-- resource
|-- core
|   `-- di
|-- data
|   |-- dao
|   |-- datasource
|   |   |-- characterdetail
|   |   `-- characterlist
|   |-- entity
|   |-- interceptor
|   `-- service
|-- dto
|-- mapper
|-- ui
|   |-- characterdetail
|   |   |-- adapter
|   |   |-- repository
|   |   |-- usercase
|   |   |-- view
|   |   `-- viewmodel
|   `-- characterlist
|       |-- adapter
|       |-- repository
|       |-- usecase
|       |-- view
|       `-- viewmodel
`-- util

````

## CI
Se realizó la configuración de Github Actions para correr los test y generar una APK que puede ser descargada.

## Testing
Se realizó el test de los ViewModels y UseCases, para el caso del listado de personajes fue utilizado Junit con la libreria Mockk y para el detalle de los personajes fue utilizado Junit con Mockito.
* * *
## Imagenes demostrativas

## Modo Claro
![marvel_app_list_light_2_192x400](https://user-images.githubusercontent.com/7023198/138609108-d1fdf1a6-60f5-43e2-be6c-d356d82322aa.jpeg)
![marvel_app_detail_llight_192x400](https://user-images.githubusercontent.com/7023198/138609124-a5fb5215-ab33-43fa-8fe5-3476d9e5d007.jpeg)

## Modo Oscuro
![marvel_app_list_dark_192x400](https://user-images.githubusercontent.com/7023198/138609149-83c83dad-cd2d-451a-a6a3-c6080fcb4b9f.jpeg)
![marvel_app_detail_dark_192x400](https://user-images.githubusercontent.com/7023198/138609168-1c2ca180-a601-43d2-8111-360457361da4.jpeg)

* * *
## Videos demostrativos

### Modo Claro
![marvel_app_video_light](https://user-images.githubusercontent.com/7023198/138609451-54bd165a-4045-47b6-9192-1f3e90e571f3.gif)


### Modo Oscuro
![marvel_app_video_dark](https://user-images.githubusercontent.com/7023198/138609354-f073220f-c091-45a2-8aac-078d18695b72.gif)


