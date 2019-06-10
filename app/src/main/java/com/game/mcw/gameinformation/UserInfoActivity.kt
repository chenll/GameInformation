package com.game.mcw.gameinformation

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.os.ConfigurationCompat
import com.game.mcw.gameinformation.databinding.ActivityLoginBinding
import com.game.mcw.gameinformation.databinding.ActivityUserinfoBinding
import com.game.mcw.gameinformation.utils.GlideUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper

class UserInfoActivity : BaseActivity<ActivityUserinfoBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_userinfo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolBar()
        GlideUtil.loadCircleHeadPic("http://cdn.duitang.com/uploads/item/201610/03/20161003000301_Wfm5X.jpeg", mBinding.ivHead)
    }

    private fun initToolBar() {
        setSupportActionBar(mBinding.includeToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.includeToolbar.toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        mBinding.includeToolbar.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun initStatusBar() {
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
    }

}