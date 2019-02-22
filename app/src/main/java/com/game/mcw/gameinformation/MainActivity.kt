package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import com.game.mcw.gameinformation.adapter.HomeFragmentPagerAdapter
import com.game.mcw.gameinformation.databinding.ActivityMainBinding
import java.util.*

class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private var mFragments: MutableList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initBottomTabs()
        mBinding.viewStart.layoutParams.height = getStatusbarHeight()
        mFragments.add(HomeRootFragment())
        mFragments.add(GameFragment())
        mFragments.add(CircleFragment())
        mFragments.add(MyFragment())
        mBinding.vpFragment.offscreenPageLimit = mFragments.size
        mBinding.vpFragment.adapter = HomeFragmentPagerAdapter(supportFragmentManager, mFragments)


    }


    private fun initBottomTabs() {
        val navigationController = mBinding.tabBottom.material()
                .addItem(android.R.drawable.ic_menu_camera, "首页")
                .addItem(android.R.drawable.ic_menu_compass, "游戏")
                .addItem(android.R.drawable.ic_menu_search, "圈子")
                .addItem(android.R.drawable.ic_menu_help, "我的")
                .build()
        navigationController.setupWithViewPager(mBinding.vpFragment)
    }
}