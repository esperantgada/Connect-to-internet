package com.example.android.marsphotos.network

import com.squareup.moshi.Json

/**
 * The JSON response is presented in XML format or another format and need to be converted
 * in an object which kotlin will use.
 * Moshi library convert JSON response in an object.
 * The converted response is stored in a data class called MarsPhotos
 */

data class MarsPhotos(val id : String, @Json(name = "img_src") val imgSrcUrl : String)