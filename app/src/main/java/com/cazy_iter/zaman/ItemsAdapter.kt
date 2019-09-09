package com.cazy_iter.zaman

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ItemsAdapter(private val context: Context,
                   private val items: ArrayList<ItemsModel>)
    : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
            ViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item, p0, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = items[position].name
        try {
            Picasso.get()
                    .load(items[position].image)
                    .into(holder.image)
        } catch (err: Exception) {}
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.itemTV)!!
        val image = itemView.findViewById<ImageView>(R.id.itemIV)!!
    }

}