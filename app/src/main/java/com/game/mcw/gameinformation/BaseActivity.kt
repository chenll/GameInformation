package com.game.mcw.gameinformation

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import org.greenrobot.eventbus.EventBus

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    lateinit var mBinding: T
    private val loadingDialog: QMUITipDialog by lazy {
        QMUITipDialog.Builder(this).setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING).setTipWord("正在加载...").create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initStatusBar()
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())

    }

    fun getStatusbarHeight(): Int = QMUIStatusBarHelper.getStatusbarHeight(this)
    abstract fun getLayoutId(): Int

    open fun initStatusBar() {
//        QMUIStatusBarHelper.translucent(this)
//        QMUIStatusBarHelper.setStatusBarDarkMode(this)
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
    }

    fun startActivity(cls: Class<*>) {
        super.startActivity(Intent(this, cls))
    }

    fun showLoading() {
        if (loadingDialog.isShowing) {
            return
        }
        loadingDialog.show()
    }


    fun hideLoading() {
        loadingDialog.dismiss()
    }

    fun registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        if (loadingDialog.isShowing) {
            hideLoading()
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        super.onDestroy()
    }
}