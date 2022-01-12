package com.example.final_project.data

import com.squareup.moshi.Json

data class Response(

	@Json(name="cities")
	val cities: List<CitiesItem?>? = null
)

data class CitiesItem(

	@Json(name="cityName")
	val cityName: String? = null,



	@Json(name="cityImage")
	val cityImage: String? = null,

	@Json(name="weather")
	val weather: String? = null,

	@Json(name="timeZone")
	val timeZone: String? = null,

	@Json(name="currency")
	val currency: String? = null,

	@Json(name="information")
	val information: String? = null,

	@Json(name="poster_path")
	val posterPath: String? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="population")
	val population: String? = null
)
