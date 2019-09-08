package com.cazy_iter.zaman

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainGroupItemsView(val context: Context, category: CategoryModel) {

    val view = View.inflate(context, R.layout.main_group_items, null)!!
    private val categoryTV = view.findViewById<TextView>(R.id.categoryTV)!!
    private val categoryRV = view.findViewById<RecyclerView>(R.id.categoryRV)!!

    init {
        categoryTV.text = category.name
        categoryRV.setHasFixedSize(true)
        categoryRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val queue = Volley.newRequestQueue(context)
        val mealsRequest = JsonObjectRequest(APIs.CATEGORIES + category.id, null, {

            val mealsJson = it.getJSONArray("meals")
            val meals = ArrayList<ItemsModel>()
            for (i in 0 until mealsJson.length()) {
                val item = mealsJson.getJSONObject(i)
                meals.add(ItemsModel(item.getString("_id"),
                        item.getString("nameEN"),
                        item.getString("image"),
                        item.getString("locationID"),
                        item.getString("descriptionAR")))
            }
            categoryRV.adapter = ItemsAdapter(context, meals)
            queue.cancelAll("meals")
        }, {
            queue.cancelAll("meals")
        })
        mealsRequest.tag = "meals"
        queue.add(mealsRequest)
    }



}