package com.cazy_iter.zaman

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val mFragmentList: MutableList<Fragment> = mutableListOf()

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }

    override fun getItem(p0: Int): Fragment = mFragmentList[p0]

    override fun getCount(): Int = mFragmentList.size

}