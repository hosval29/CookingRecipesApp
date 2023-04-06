# CookingRecipesApp

[![Kotlin](https://img.shields.io/badge/kotlin-1.8.0-blue.svg)](http://kotlinlang.org) [![Gradle](https://img.shields.io/badge/gradle-7.5.0-%2366DCB8.svg)](https://developer.android.com/studio/releases/gradle-plugin)

:white_check_mark: 100% en **Kotlin**

:white_check_mark: Consumo de web services con **Retrofit**

:white_check_mark: Clean **architecture**

:white_check_mark: Patrón de arquitectura **MVVM**

:white_check_mark: Inyección de dependencia con **Hilt**

:white_check_mark: Clean **code**

## Instrucciones

Estas instrucciones le proporcionarán una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y pruebas.

### Pre requisitos 📋
- [Android Studio](https://developer.android.com/studio/)
- [Git](https://git-scm.com/downloads)

### Instalación 🔧
Después de descargar Android Studio y git en su máquina local, ejecute el comando:

> git clone https://github.com/hosval29/CookingRecipesApp.git

Abrir Android Studio y haga clic en la opción **Abrir un proyecto existente en Android Studio** y seleccione la carpeta donde se encuentra clonado el proyecto.

## Estructura del proyecto

![](https://i.imgur.com/ECCcRbt.png)

El proyecto esta desarrollado con **Clean Architecture** que consta de 3 capas que están estructuradas por paquetes según el feature.

### Capa de presentación o UI :iphone:
La capa de presentación es la encargada de mostrar los datos e interactuar con la interfaz de usuario donde usamos el patrón de arquitectura Model-ViewModel-View (MVVM) usando los componentes de arquitectura de Android donde encontramos los siguientes elementos:

- Activity
- Screen
- ViewModel
- Model

### Sobre la capa de presentacion
El desarrollo del Screen es 100% en Jetpack compose, de esta manera tenemos un modelo de vista que es declarativa lo cual nos ayuda en la experiencia del usuario, realizar muchas más cosas con menos código y mejorar los tiempo de compilación de un proyecto.

- Jetpack Compose
- Jetpack

### Capa de dominio :large_orange_diamond:
La capa de dominio es la que se encarga de ejecutar la logica de negocio e interactúa con las capas de data y presentación donde encontramos los siguientes elementos:

- Use Cases
- Domain Entity

### Capa de datos :open_file_folder:
La capa de datos es la que se encarga de gestionar los datos locales y remotos de la aplicación, usando **Retrofit** para el consumo de web Services y Room para guardar datos del usuario donde encontramos los siguientes elementos:

- Repository
- Data Entity
- RestApi
- Room

## Bibliotecas y SDK usados en el proyecto:

- Diseño
    - [Material components](https://developer.android.com/jetpack/androidx/releases/compose-material3?hl=es-419)

- Arquitectura:
    - [State](https://developer.android.com/jetpack/compose/state?hl=es-419)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)

- Inyección de dependencia:
    - [Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=es-419)

- Net
    - [Retrofit](https://github.com/square/retrofit)
    - [coil](https://github.com/coil-kt/coil)

- Serialización / Deserialización
    - [Gson](https://github.com/google/gson)

- Programación asincrona:
    - [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)

- Maps:
- [Compose GoogleMaps](https://developers.google.com/maps/documentation/android-sdk/maps-compose?hl=es-419)
