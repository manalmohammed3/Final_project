package com.example.final_project

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_project.data.CitiesItem
import com.example.final_project.overview.cityApiStatus


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data:List<CitiesItem>? ){

    val adapter=recyclerView.adapter as CityAdapter
    adapter.submitList(data)
}




@BindingAdapter("photoUrl")
fun ImageView.bind(photoUrl: String?) {
    Log.e("tag", "Starthere ${photoUrl}")
    photoUrl?.let {
        val photoUri = photoUrl.toUri().buildUpon().build()
        Glide.with(this.context)
            .load(photoUri)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(this)
    }

}

@BindingAdapter("cityApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: cityApiStatus?){
    when (status){
        cityApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        cityApiStatus.DONE ->{
            statusImageView.visibility = View.GONE
        }
        cityApiStatus.ERROR ->{
            statusImageView.visibility = View.GONE
            statusImageView.setImageResource(R.drawable.ic_baseline_broken_image_24)
        }
    }
}