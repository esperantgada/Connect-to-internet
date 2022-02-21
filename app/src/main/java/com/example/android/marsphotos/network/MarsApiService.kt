package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

/**
 * Creates [Moshi] object. [Retrofit] will use this to parse or convert [JSON] response into [Kotlin]
 * object
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 *   Retrofit builder to build and create a Retrofit object
 *   This will be used to communicate with the API
 */

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


/**
 * This will help retrofit to talk to the API
 */
interface MarsApiService{
    @GET("photos")
    suspend fun getPhotos() : List<MarsPhoto>
}

/**
 * Initialize [retrofit] object and its only one instance will be accessed from the rest of the app
 */
object MarsApi{
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}