package com.example.final_project.tripadapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
import com.example.final_project.planmodel.Trip
import com.example.final_project.tripplane.PlanFragmentDirections

class Adapter (val context: Context, val dataSet:List<Trip> ): RecyclerView.Adapter<Adapter.TripViewHolder>() {

    class TripViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val toDoTitle : TextView = view.findViewById(R.id.task_title)
        val toDoDate: TextView = view.findViewById(R.id.task_date)
        val card : CardView = view.findViewById(R.id.item_card)
        val edit : ImageView = view.findViewById(R.id.edit_icon)
        val done : ImageView = view.findViewById(R.id.icon_done)
        val isPast : TextView = view.findViewById(R.id.past_coming)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val adapter= LayoutInflater.from(parent.context).inflate(R.layout.plan_item,parent,false)
        return TripViewHolder(adapter)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val item = dataSet[position]
        holder.toDoTitle.text = item.title
        holder.toDoDate.text = item.dueDate
        if (item.isPast){
            holder.isPast.setTextColor(Color.parseColor("#C6342A"))
            holder.isPast.text = context.getString(R.string.past)
        }
        if (item.isComplete){
            holder.done.setImageResource(R.drawable.plan_check)
        }

        holder.card.setOnClickListener{
            val action = PlanFragmentDirections.actionPlanFragmentToShowPlanFragment()
            holder.card.findNavController().navigate(action)

        }

        holder.edit.setOnClickListener {
            val action = PlanFragmentDirections.actionPlanFragmentToEditPlanFragment()
            holder.edit.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int = dataSet.size

}