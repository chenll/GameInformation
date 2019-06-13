package com.game.mcw.gameinformation

import android.os.Bundle
import android.support.v4.app.Fragment
import com.game.mcw.gameinformation.adapter.HomeFragmentPagerAdapter
import com.game.mcw.gameinformation.databinding.ActivityMainBinding
import java.util.*

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private var mFragments: MutableList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomTabs()
        mBinding.viewStart.layoutParams.height = getStatusbarHeight()
//        mFragments.add(HomeRootFragment())
        mFragments.add(NewsFragment())
        mFragments.add(GameFragment())
        mFragments.add(GameGiftFragment())
        mFragments.add(MyFragment())
        mBinding.vpFragment.offscreenPageLimit = mFragments.size
        mBinding.vpFragment.adapter = HomeFragmentPagerAdapter(supportFragmentManager, mFragments)
//        startActivity(TestActivity::class.java)


    }


    private fun initBottomTabs() {
        val navigationController = mBinding.tabBottom.material()
                .addItem(android.R.drawable.ic_menu_camera, "首页")
                .addItem(android.R.drawable.ic_menu_compass, "游戏")
                .addItem(android.R.drawable.ic_menu_search, "礼包")
                .addItem(android.R.drawable.ic_menu_help, "我的")
                .build()
        navigationController.setupWithViewPager(mBinding.vpFragment)
    }
}