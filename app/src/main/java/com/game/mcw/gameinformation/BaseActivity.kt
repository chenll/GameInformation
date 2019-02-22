package com.game.mcw.gameinformation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.qmuiteam.qmui.util.QMUIStatusBarHelper

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        QMUIStatusBarHelper.translucent(this)
        super.onCreate(savedInstanceState)

    }

    fun getStatusbarHeight(): Int = QMUIStatusBarHelper.getStatusbarHeight(this)

}