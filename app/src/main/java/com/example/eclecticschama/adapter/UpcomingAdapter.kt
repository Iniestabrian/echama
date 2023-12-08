package com.example.eclecticschama.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eclecticschama.R
import com.example.eclecticschama.data.CategoryItem
import com.example.eclecticschama.data.UpcomingItem

class UpcomingAdapter(private val upcomingList: MutableList<UpcomingItem>): RecyclerView.Adapter<UpcomingAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.upcoming_design,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
      return  upcomingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = upcomingList[position]
        val formattedAmountDue = holder.itemView.context.getString(R.string.amount_due, currentItem.amountDue)
        val formattedDueDate = holder.itemView.context.getString(R.string.due_date, currentItem.dueDate)
        val formattedPenalty = holder.itemView.context.getString(R.string.penalty_amount, currentItem.penaltyAmount)

        holder.amountDue.text =formattedAmountDue
        holder.dueDate.text = formattedDueDate
        holder.penalty.text = formattedPenalty


    }

  class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

      val dueDate: TextView = itemView.findViewById(R.id.tvDueDate)
      val amountDue: TextView = itemView.findViewById(R.id.tvAmountDue)
      val penalty: TextView = itemView.findViewById(R.id.tvpenaltyNumber)

    }


    fun removeItem(position: Int) {
        upcomingList.removeAt(position)
        notifyItemRemoved(position)
    }
}