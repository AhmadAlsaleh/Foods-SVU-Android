package com.cazy_iter.zaman

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class LauncherActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        Statics.setRTL_LTR(this)

        getLocations()

    }

    private fun getLocations() {
        val queue = Volley.newRequestQueue(this)
        val locationsArrayRequest = JsonArrayRequest(APIs.LOCATIONS, {

            Statics.locations.clear()
            for (i in 0 until it.length()) {
                val mainLocationJSON = it.getJSONObject(i)
                Statics.locations.add(
                        LocationModel(this,
                                mainLocationJSON.getString("_id"),
                                mainLocationJSON.getString("name" + Statics.getCurrentLanguageName(this)),
                                mainLocationJSON.getJSONArray("subLocations")
                        )
                )
            }
            queue.cancelAll("loc")
            getMainItems()
        }, {
            queue.cancelAll("loc")
            Log.e("locations", it.toString())
            getLocations()
        })
        locationsArrayRequest.tag = "loc"
        queue.add(locationsArrayRequest)
    }

    private fun getMainItems() {
        val queue = Volley.newRequestQueue(this)
        val mainRequest = JsonArrayRequest(APIs.MAIN, {

            Log.d("main items", it.toString())

            Statics.mainItems.clear()
            for (i in 0 until it.length()) {
                val item = it.getJSONObject(i)
                Statics.mainItems.add(
                        ItemsModel(
                                item.getString("_id"),
                                item.getString("name" + Statics.getCurrentLanguageName(this)),
                                item.getString("image"),
                                item.getString("locationID"),
                                item.getString("content"),
                                item.getString("description" + Statics.getCurrentLanguageName(this))
                        )
                )
            }
            queue.cancelAll("main")
            getAllCategories()

        }, {
            Log.e("err", it.toString())
            queue.cancelAll("main")
            getMainItems()
        })
        mainRequest.tag = "main"
        queue.add(mainRequest)
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
                                category.getString("name" + Statics.getCurrentLanguageName(this)),
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
