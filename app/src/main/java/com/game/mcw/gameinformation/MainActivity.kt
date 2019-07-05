package com.game.mcw.gameinformation

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.game.mcw.gameinformation.adapter.HomeFragmentPagerAdapter
import com.game.mcw.gameinformation.dialog.HomePopUpDialog
import com.game.mcw.gameinformation.dialog.QuitPopUpDialog
import com.game.mcw.gameinformation.modle.IndexCommon
import org.litepal.LitePal
import java.util.*

class MainActivity : BaseActivity<com.game.mcw.gameinformation.databinding.ActivityMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private var mFragments: MutableList<Fragment> = ArrayList()
    private var mIndexCommon: IndexCommon? = null
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
        if (mIndexCommon != null) {
            QuitPopUpDialog().apply {
                arguments = Bundle().apply {
                    putParcelable("indexCommon", mIndexCommon)
                }
                show(this@MainActivity.supportFragmentManager, "homepopupdialog")
            }
            return true
        }

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

    fun setQuits(quits: List<IndexCommon>?) {
        if (quits == null || quits.isEmpty()) {
            return
        }
        for (start in quits.toTypedArray().filter { it.isEffectived() }) {
            Glide.with(this).load(start.image).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).listener(object : RequestListener<Drawable> {
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    resource?.let { mIndexCommon = start }
                    return false
                }

                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    return false
                }

            }).preload()
            break
        }
    }
}