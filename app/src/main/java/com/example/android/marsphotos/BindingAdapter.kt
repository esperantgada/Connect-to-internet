package com.example.android.marsphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.network.MarsPhoto
import com.example.android.marsphotos.overview.MarsApiStatus
import com.example.android.marsphotos.overview.MarsPhotoAdapter

/**
 * This sets a custom attribute for the [imageView] in the [XML] file
 * We take the [image] [URL] and convert it a [URI] using [HTTPS] scheme
 * After, we call [load] method from [Coil] library to load the appropriate image
 */
@BindingAdapter("imageUrl")
fun bindImage(image : ImageView, imgUrl : String?){
    imgUrl?.let {
        val imageUri = imgUrl.toUri().buildUpon().scheme("https").build()
        image.load(imageUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}


/**
 * Takes a list of photos with id and Url and puts this list in the recyclerView through custom attribute
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView : RecyclerView, data :  List<MarsPhoto>?){
    val adapter = recyclerView.adapter as MarsPhotoAdapter
    adapter.submitList(data)
}


/**
 * Handles API response status
 */
@BindingAdapter("marsApiStatus")
fun bindStatus(statusImage : ImageView, status : MarsApiStatus?){
    when(status){
        MarsApiStatus.LOADING -> {
            statusImage.visibility = View.VISIBLE
            statusImage.setImageResource(R.drawable.loading_animation)
        }

        MarsApiStatus.ERROR -> {
            statusImage.visibility = View.VISIBLE
            statusImage.setImageResource(R.drawable.ic_connection_error)
        }

        MarsApiStatus.DONE -> {
            statusImage.visibility = View.GONE
        }
    }
}

