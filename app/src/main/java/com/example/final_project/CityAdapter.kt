package com.example.final_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.ItemBinding
import com.example.final_project.network.ResultsItem

class CityAdapter :ListAdapter<ResultsItem, CityAdapter.CityPhotosViewHolder>(DiffCallback) {
    /**
     * prepare the positions of the views
     */
    class CityPhotosViewHolder(var binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cityPhoto: ResultsItem) {
            binding.item = cityPhoto
            binding.executePendingBindings()
        }

//        var myImage = binding.myImage
    }

    /**
     * check the new and the old data taken from the API
     */
    companion object DiffCallback : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.posterPath == newItem.posterPath
        }
    }

    /**
     * on creationg the viewHolder takes the views from the [GridViewItemBinding]
     */
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CityPhotosViewHolder {
        return CityPhotosViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * binding the list of data to the item view
     */
    override fun onBindViewHolder(holder: CityPhotosViewHolder, position: Int) {
        val moviePhoto = getItem(position)
        holder.bind(moviePhoto)
//        holder.myImage.setOnClickListener {
//            val action = OverViewFragmentDirections.actionOverViewFragmentToOverViewDetailFragment(position)
//            holder.itemView.findNavController().navigate(action)
//        }
    }
}