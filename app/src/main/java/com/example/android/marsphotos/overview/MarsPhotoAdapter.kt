package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.MarsPhoto

class MarsPhotoAdapter : ListAdapter<MarsPhoto, MarsPhotoAdapter.MarsPhotoViewHolder>(DiffCallback){

    class MarsPhotoViewHolder(private val binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root){

            fun bind(marsPhoto: MarsPhoto){
                binding.photo = marsPhoto
                binding.executePendingBindings()
            }
        }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MarsPhotoAdapter.MarsPhotoViewHolder {
        val inflatedLayout = GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        return MarsPhotoViewHolder(inflatedLayout)
    }

    override fun onBindViewHolder(holder: MarsPhotoAdapter.MarsPhotoViewHolder, position: Int) {
        val marsImage = getItem(position)
        holder.bind(marsImage)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>(){
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean =
            oldItem.imageSrcUrl == newItem.imageSrcUrl

    }

}