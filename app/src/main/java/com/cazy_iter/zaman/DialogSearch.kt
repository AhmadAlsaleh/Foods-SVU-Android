package com.cazy_iter.zaman

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.dialog_search.*
import org.json.JSONObject

class DialogSearch(context: Context?) : Dialog(context) {

    private var locationID = ""
    private var categoryID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_search)

        val loading = DialogLoading(context)

        setupSpinner()
        setupLocations()
        searchBTN.setOnClickListener {
            val findObject = JSONObject()
            findObject.put("locationID", locationID)
            findObject.put("categoryID", categoryID)
            findObject.put("key", searchTV.text.toString())

            loading.show()
            val queue = Volley.newRequestQueue(context)
            val findMealRequest = JsonObjectRequest(Request.Method.POST, APIs.FIND_MEAL, findObject, {

                Log.d("find", it.getJSONArray("res").toString())
                loading.dismiss()
                queue.cancelAll("find")

                context.startActivity(Intent(context, ResultsActivity::class.java)
                        .putExtra("res", it.getJSONArray("res").toString()))

            }, {
                Toast.makeText(context, "Try again, please", Toast.LENGTH_SHORT).show()
                loading.hide()
                queue.cancelAll("find")
            })
            findMealRequest.tag = "find"
            queue.add(findMealRequest)
        }

    }

    private fun setupLocations() {
        val spinnerMainItems = ArrayList<String>()
        spinnerMainItems.add("All Regions")
        for (main in Statics.locations) {
            spinnerMainItems.add(main.name)
        }
        val adapter = ArrayAdapter<String>(context!!,
                R.layout.support_simple_spinner_dropdown_item, spinnerMainItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mainLocationSP.adapter = adapter
        mainLocationSP.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.e("main selected", locationID)
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 0) {
                    subLocationSP.visibility = View.GONE
                    locationID = ""
                } else {
                    subLocationSP.visibility = View.VISIBLE
                    setupSubLocations(p2 - 1)
                }
            }

        }
    }

    private fun setupSubLocations(position: Int) {
        val spinnerSubItems = ArrayList<String>()

        for (sub in Statics.locations[position].getSubLocations()) {
            spinnerSubItems.add(sub.name)
        }

        val adapter = ArrayAdapter<String>(context!!,
                R.layout.support_simple_spinner_dropdown_item, spinnerSubItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        subLocationSP.adapter = adapter
        subLocationSP.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.e("location selected", locationID)
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                locationID = Statics.locations[position].getSubLocations()[p2].id
            }

        }
    }

    private fun setupSpinner() {
        val spinnerItems = ArrayList<String>()
        spinnerItems.add("All Categories")
        for (category in Statics.categoryModels) {
            spinnerItems.add(category.name)
        }
        val adapter = ArrayAdapter<String>(context!!,
                R.layout.support_simple_spinner_dropdown_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        searchSP.adapter = adapter
        searchSP.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.e("selected", categoryID)
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.e("position", "$position")
                categoryID = if (position == 0) { "" }
                    else { Statics.categoryModels[position - 1].id }
            }

        }
    }

}