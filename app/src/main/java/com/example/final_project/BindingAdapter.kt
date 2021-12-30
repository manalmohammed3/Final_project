package com.example.final_project

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.network.ResultsItem


@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data:List<ResultsItem>
){
    val adapter=recyclerView.adapter as CityAdapter
    adapter.submitList(data)
}