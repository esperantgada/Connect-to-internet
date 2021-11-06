package com.example.android.marsphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.network.MarsPhotos
import com.example.android.marsphotos.overview.MarsApiStatus
import com.example.android.marsphotos.overview.PhotoGridAdapter

/**
 * Using the Coil image library to load image.
 */

@BindingAdapter("imageUrl")
fun bindImage(imgView : ImageView, imgUrl : String?){

    imgUrl?.let {
        //Convert the URL to uri
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }

}


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data : List<MarsPhotos>?){

    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)

}


@BindingAdapter("marsApiStatus")
fun bindStatus(statusImage : ImageView, status : MarsApiStatus? ){

    when (status){

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