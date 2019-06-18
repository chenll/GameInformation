package com.game.mcw.gameinformation

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    lateinit var mBinding: T
    private var loadingDialog: QMUITipDialog? = null

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

    fun startActivity(cls: Class<*>) {
        super.startActivity(Intent(this, cls))
    }

    fun showLoading(message: String = "正在加载...") {
        if (loadingDialog == null) {
            loadingDialog = QMUITipDialog.Builder(this).setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING).setTipWord(message).create()
        }
        if (loadingDialog!!.isShowing) {
            return
        }
        loadingDialog?.show()
    }


    fun hideLoading() {
        loadingDialog?.dismiss()
    }

    override fun onDestroy() {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            hideLoading()
        }
        super.onDestroy()
    }
}