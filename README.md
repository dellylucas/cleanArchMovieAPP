# cleanArchMovieAPP

Aplicacion Android sobre una lista-detalle de peliculas populares consumiendo API de la pagina

<img src="https://www.themoviedb.org/assets/2/v4/logos/v2/blue_square_2-d537fb228cf3ded904ef09b136fe3fec72548ebc1fea3fbbd1ad9e36364db38b.svg" width="150">

[themoviedb](https://developers.themoviedb.org/3/movies/get-popular-movies)  <-- API


Tiene una arquitectura Clean de la siguiente forma

<img src="https://user-images.githubusercontent.com/21035435/69536839-9f4c8e80-0fa0-11ea-85ee-d7823e5a46b0.png" width="600">

con un patron de presentacion MVVM
usando componentes de AndroidX

* Coroutines
* Livedata
* Suspended Functions
* viewBinding
* Inyeccion de dependencias con hilt
* Navigation
* Retrofit
* ORM usando Room
* Databinding
* kotlin DSL

Se emplearon pruebas unitarias, de integracion entre componentes e instrumentadas 
* JUnit
* Mockk
* 
...

##### Nota: Al clonar el proyecto se debe crear en el local.properties una variable API_KEY que se generara en el sitio oficial [themoviedb](https://developers.themoviedb.org/3/movies/get-popular-movies)
