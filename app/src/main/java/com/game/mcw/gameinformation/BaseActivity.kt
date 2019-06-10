package com.game.mcw.gameinformation

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.qmuiteam.qmui.util.QMUIStatusBarHelper

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    lateinit var mBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        initStatusBar()
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())

    }

    fun getStatusbarHeight(): Int = QMUIStatusBarHelper.getStatusbarHeight(this)
    abstract fun getLayoutId(): Int

    open fun initStatusBar() {
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarDarkMode(this)
    }

}