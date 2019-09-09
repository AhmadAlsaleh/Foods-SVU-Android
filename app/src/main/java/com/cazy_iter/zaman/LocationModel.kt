package com.cazy_iter.zaman

import org.json.JSONArray

class LocationModel(val id: String, val name: String, private val subLocations: JSONArray?) {

    fun getSubLocations(): ArrayList<LocationModel> {

        val subs = ArrayList<LocationModel>()
        for (i in 0 until subLocations!!.length()) {
            subs.add(
                    LocationModel(
                            subLocations.getJSONObject(i).getString("_id"),
                            subLocations.getJSONObject(i).getString("nameAR"),
                            null
                    )
            )
        }
        return subs
    }

}