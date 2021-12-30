package com.example.final_project.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://wft-geo-db.p.rapidapi.com"

private const val API_KEY = "77eaf4d692mshbe758ebe76c861fp1e1234jsnf7b888c4726b"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
// for using retroit library
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/** for getting the data from the url
 * */
interface cityurls {
    @GET("/3/city/{sort}?api_key=${API_KEY}&language=en-US")
    suspend fun getCityList(@Path("sort") sort: String) : Response
    @GET("/3/discover/city?api_key=${API_KEY}")
    suspend fun getCityGenersList(@Query("with_genres") genreId: Int) : Response
}

object cityApi {
    val retrofitService: cityurls by lazy {
        retrofit.create(cityurls::class.java)
    }
}