package com.cazy_iter.zaman

import android.content.Context
import org.json.JSONArray

class LocationModel(val context: Context, val id: String, val name: String, private val subLocations: JSONArray?) {

    fun getSubLocations(): ArrayList<LocationModel> {

        val subs = ArrayList<LocationModel>()
        for (i in 0 until subLocations!!.length()) {
            subs.add(
                    LocationModel(context,
                            subLocations.getJSONObject(i).getString("_id"),
                            subLocations.getJSONObject(i).getString("name" + Statics.getCurrentLanguageName(context)),
                            null
                    )
            )
        }
        return subs
    }

}