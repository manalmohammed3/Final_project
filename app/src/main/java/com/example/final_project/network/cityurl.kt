package com.example.final_project.network

import com.example.final_project.data.CitiesItem
import com.example.final_project.data.Response
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://final-project-2cc60-default-rtdb.europe-west1.firebasedatabase.app/"

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
   @GET("data")
    suspend fun getCityList() : Response

}

object cityApi {
    val retrofitService: cityurls by lazy {
        retrofit.create(cityurls::class.java)
    }

}