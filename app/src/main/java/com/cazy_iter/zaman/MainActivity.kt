package com.cazy_iter.zaman

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Statics.setRTL_LTR(this) // language

        // region categories and items
        val mainPager = MainPagerAdapter(supportFragmentManager)
        for (item in Statics.mainItems) {
            mainPager.addFragment(MainFragment(item))
        }
        pager.adapter = mainPager
        worm_dots_indicator.setViewPager(pager)

        for (category in Statics.categoryModels) {
            mainLL.addView(
                    MainGroupItemsView(
                            this,
                            category
                    ).view
            )
        }
        // endregion

        searchFAB.setOnClickListener {
            DialogSearch(this).show()
        }

        settingsIV.visibility = View.GONE
        settingsIV.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
            finish()
        }

    }
}
