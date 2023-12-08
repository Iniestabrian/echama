package com.example.eclecticschama.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.eclecticschama.R
import com.example.eclecticschama.data.CategoryItem


class CategoryAdapter(private val categoryList: List<CategoryItem>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_recycler_design,parent,false)
        return CategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
      return  categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem : CategoryItem = categoryList[position]
        holder.imageView.setImageResource(currentItem.imageResId)
        holder.textView.text = currentItem.categoryName


    }


    class CategoryViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

     val cardView: CardView = itemView.findViewById(R.id.cardView)
      val imageView: ImageView = itemView.findViewById(R.id.imgCategory)
      val textView: TextView = itemView.findViewById(R.id.tvCategoryName)

    }

}