package com.cazy_iter.zaman

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_preview.*
import kotlinx.android.synthetic.main.content_item_preview.*

class ItemPreviewActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_preview)

        val item = intent.getSerializableExtra("item") as ItemsModel

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        backIV.setOnClickListener {
            onBackPressed()
        }

        itemNameTV.text = item.name
        try {
            Picasso.get()
                    .load(item.image)
                    .into(itemImageIV)
        } catch (err: Exception) {}
        itemContentTV.text = "المكونات:\n" + item.content
        itemDescriptionTV.text = "طريقة التحضير:\n" + item.description
        getLocation(item.locationID)

    }

    private fun getLocation(locationID: String) {
        if (locationID.isNotEmpty()) {
            val queue = Volley.newRequestQueue(this)
            val locationRequest = JsonObjectRequest(APIs.LOCATIONS + locationID, null, {
                queue.cancelAll("loc")
                itemLocationTV.text = it.getString("name" + Statics.getCurrentLanguageName(this))
            }, {
                queue.cancelAll("loc")
                Log.e("getLocation", it.toString())
                getLocation(locationID)
            })
            locationRequest.tag = "loc"
            queue.add(locationRequest)
        }
    }
}
