package com.game.mcw.gameinformation.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class HomeFragmentPagerAdapter(fragmentManager: FragmentManager, fragments: List<Fragment>) : FragmentPagerAdapter(fragmentManager) {
    private var mfragments: List<Fragment> = fragments
    var titles = arrayOf("资讯","视频","攻略")

    override fun getItem(p0: Int): Fragment {
        return mfragments[p0]
    }

    override fun getCount(): Int {
        return mfragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}