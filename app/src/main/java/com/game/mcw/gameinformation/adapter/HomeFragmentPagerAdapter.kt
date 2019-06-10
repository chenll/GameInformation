package com.game.mcw.gameinformation.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class HomeFragmentPagerAdapter(fragmentManager: FragmentManager, fragments: List<Fragment>) : FragmentPagerAdapter(fragmentManager) {
    private var mFragments: List<Fragment> = fragments
    var mTitles = arrayOf("资讯","视频","攻略")

    override fun getItem(p0: Int): Fragment {
        return mFragments[p0]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }
}