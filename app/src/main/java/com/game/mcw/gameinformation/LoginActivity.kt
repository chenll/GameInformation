package com.game.mcw.gameinformation

import android.os.Bundle
import com.game.mcw.gameinformation.databinding.ActivityLoginBinding
import com.qmuiteam.qmui.util.QMUIStatusBarHelper

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initStatusBar() {
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
    }
}