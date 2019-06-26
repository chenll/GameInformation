package com.game.mcw.gameinformation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.widget.Toast
import com.game.mcw.gameinformation.adapter.HomeFragmentPagerAdapter
import java.util.*

class MainActivity : BaseActivity<com.game.mcw.gameinformation.databinding.ActivityMainBinding>() {
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
//        mFragments.add(GameFragment())
        mFragments.add(GameGiftFragment())
        mFragments.add(MyFragment())
        mBinding.vpFragment.offscreenPageLimit = mFragments.size
        mBinding.vpFragment.adapter = HomeFragmentPagerAdapter(supportFragmentManager, mFragments)
//        startActivity(TestActivity::class.java)
    }


    private fun initBottomTabs() {
        val navigationController = mBinding.tabBottom.material()
                .addItem(android.R.drawable.ic_menu_camera, "首页")
//                .addItem(android.R.drawable.ic_menu_compass, "游戏")
                .addItem(android.R.drawable.ic_menu_search, "礼包")
                .addItem(android.R.drawable.ic_menu_help, "我的")
                .build()
        navigationController.setupWithViewPager(mBinding.vpFragment)
    }

    private var exitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(MyApplication.INSTANCE, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis()
            } else {
                finish()
            }
            return true
        }

        return super.onKeyDown(keyCode, event)
    }
}