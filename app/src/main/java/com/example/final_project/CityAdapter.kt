package com.example.final_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.data.CitiesItem
import com.example.final_project.databinding.ItemBinding
import com.example.final_project.overview.OverviewFragment
import com.example.final_project.overview.OverviewFragmentDirections
import com.google.android.gms.common.internal.safeparcel.SafeParcelable

class CityAdapter : ListAdapter<CitiesItem, CityAdapter.CityPhotosViewHolder>(DiffCallback) {


    class CityPhotosViewHolder(var binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cityImage: CitiesItem) {
            binding.item = cityImage
            binding.executePendingBindings()


        }

        var myImage = binding.cityImage

    }


    companion object DiffCallback : DiffUtil.ItemCallback<CitiesItem> (){
        override fun areItemsTheSame(oldItem: CitiesItem, newItem: CitiesItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CitiesItem, newItem: CitiesItem): Boolean {
            return oldItem.posterPath == newItem.posterPath
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): CityPhotosViewHolder {
        return CityPhotosViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * binding the list  of data to the item view
     */
    override fun onBindViewHolder(holder: CityPhotosViewHolder, position:Int) {
        val cityImage = getItem(position)

        holder.bind( cityImage)
        holder.myImage.setOnClickListener {
            val action = OverviewFragmentDirections.actionOverviewFragmentToOverviewDetailsFragment(cityImage.id!!)
            holder.itemView.findNavController().navigate(action)

            //  holder.binding.root.findNavController()
        }


    }
}