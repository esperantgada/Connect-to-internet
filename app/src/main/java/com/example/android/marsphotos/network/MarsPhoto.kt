package com.example.android.marsphotos.network

import com.squareup.moshi.Json

/**
 * Holds Mars photos
 */
data class MarsPhoto(
    val id : String,

    @Json(name = "img_src")
    val imageSrcUrl : String
)
