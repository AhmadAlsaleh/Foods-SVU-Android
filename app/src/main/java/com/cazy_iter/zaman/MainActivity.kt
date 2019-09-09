package com.cazy_iter.zaman

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    }
}
