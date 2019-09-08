package com.cazy_iter.zaman

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainPager = MainPagerAdapter(supportFragmentManager)
        mainPager.addFragment(BlankFragment(), "")
        mainPager.addFragment(BlankFragment(), "")
        mainPager.addFragment(BlankFragment(), "")
        pager.adapter = mainPager
        worm_dots_indicator.setViewPager(pager)

        for (category in Statics.categoryModels) {
            mainLL.addView(
                    MainGroupItemsView(
                            this,
                            category)
                            .view
            )
        }

    }
}
