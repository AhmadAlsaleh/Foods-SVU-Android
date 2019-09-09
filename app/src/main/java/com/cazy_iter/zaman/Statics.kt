package com.cazy_iter.zaman

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.support.annotation.RequiresApi
import java.util.*


object Statics {

    var categoryModels = ArrayList<CategoryModel>()
    var mainItems = ArrayList<ItemsModel>()
    var locations = ArrayList<LocationModel>()
    val ar = "AR"
    val en = "EN"
    private var currentLanguage = ar

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun setRTL_LTR(activity: Activity) {
        val locale: Locale = if (Statics.getCurrentLanguageName(activity) == Statics.ar) {
            Locale(Statics.getCurrentLanguageName(activity).toLowerCase())
        } else {
            Locale(Statics.getCurrentLanguageName(activity).toLowerCase())
        }
        Locale.setDefault(locale)

        val res = activity.resources
        val config = Configuration(res.configuration)
        config.locale = locale
        config.setLayoutDirection(locale)
        res.updateConfiguration(config, res.displayMetrics)

    }

    fun getCurrentLanguageName(context: Context?): String {
        if (context == null) {
            return ar
        }
        val sharedPreferences = context.getSharedPreferences("Zaman", Context.MODE_PRIVATE)
        currentLanguage = sharedPreferences.getString("lng", ar)
        return currentLanguage
    }

    fun setCurrentLanguageName(activity: Activity, name: String) {
        val editor = activity.getSharedPreferences("Zaman", Context.MODE_PRIVATE).edit()
        editor.putString("lng", name)
        editor.apply()
        currentLanguage = name
    }


}
