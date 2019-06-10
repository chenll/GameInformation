package com.game.mcw.gameinformation

import android.os.Bundle
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
        GlideUtil.loadCircleHeadPic("http://cdn.duitang.com/uploads/item/201610/03/20161003000301_Wfm5X.jpeg", mBinding.ivHead)
    }

    override fun initStatusBar() {
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
    }
}