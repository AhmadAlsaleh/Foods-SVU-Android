package com.cazy_iter.zaman

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_results.*
import org.json.JSONArray

class ResultsActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        Statics.setRTL_LTR(this)

        backIV.setOnClickListener {
            onBackPressed()
        }

        val resultsJSON = JSONArray(intent.extras.getString("res"))
        val results = ArrayList<ItemsModel>()
        for (i in 0 until resultsJSON.length()) {
            val itemJSON = resultsJSON.getJSONObject(i)
            results.add(
                    ItemsModel(
                            itemJSON.getString("_id"),
                            itemJSON.getString("name" + Statics.getCurrentLanguageName(this)),
                            itemJSON.getString("image"),
                            itemJSON.getString("locationID"),
                            itemJSON.getString("description" + Statics.getCurrentLanguageName(this))
                    )
            )
        }

        resultRV.setHasFixedSize(true)
        resultRV.layoutManager = GridLayoutManager(this, 2)
        resultRV.adapter = ItemsAdapter(this, results)

    }
}
