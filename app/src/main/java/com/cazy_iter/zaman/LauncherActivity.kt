package com.cazy_iter.zaman

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        getAllCategories()
    }

    private fun getAllCategories() {
        val queue = Volley.newRequestQueue(this)
        val categoriesArrayRequest = JsonArrayRequest(APIs.CATEGORIES, {
            Statics.categoryModels.clear()
            for (i in 0 until it.length()) {
                val category = it.getJSONObject(i)
                Statics.categoryModels.add(
                        CategoryModel(
                                category.getString("_id"),
                                category.getString("nameAR"),
                                category.getString("image")
                        )
                )
            }
            queue.cancelAll("category")
            finish()
            startActivity(Intent(this, MainActivity::class.java))

        }, {
            Log.e("err", it.toString())
            queue.cancelAll("category")
            getAllCategories()
        })
        categoriesArrayRequest.tag = "category"
        queue.add(categoriesArrayRequest)
    }

}
