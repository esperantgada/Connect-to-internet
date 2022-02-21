package com.example.android.marsphotos

import com.example.android.marsphotos.network.MarsApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * This test mocks the Api and tests if its response is not null
 * if the list is not empty and the id of an photo matches
 */
class MarsApiServiceTest : BaseTest() {
    private lateinit var service : MarsApiService

    //This will be executed before the any other functions with the class
    @Before
    fun setUp(){
       val url = mockWebServer.url("/")

        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )).build().create(MarsApiService::class.java)
    }


    @Test
    fun api_service(){
        enqueue("mars_photos.json")

        runBlocking {
            val apiResponse = service.getPhotos()
            assertNotNull(apiResponse)
            assertTrue("The list was  empty", apiResponse.isNotEmpty())
            assertEquals("The Id didn't match", "424905", apiResponse[0].id)
        }
    }
}