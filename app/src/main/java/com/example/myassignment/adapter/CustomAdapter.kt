package com.example.myassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myassignment.R
import com.example.myassignment.model.ImageModel

class CustomAdapter() :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var itemList = emptyList<ImageModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_with_id_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imgItem = itemList[position]
        holder.imageView.setImageURI(imgItem.img)
        holder.textView.text = imgItem.id.toString()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setData(item:List<ImageModel>){
        this.itemList = item
        notifyItemChanged(0)
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cardView :CardView = itemView.findViewById(R.id.card_view)
        val imageView: ImageView = itemView.findViewById(R.id.img)
        val textView: TextView = itemView.findViewById(R.id.img_id)
    }


}