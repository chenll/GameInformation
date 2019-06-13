package com.game.mcw.gameinformation

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.game.mcw.gameinformation.databinding.ActivityUserinfoBinding
import com.game.mcw.gameinformation.utils.GlideUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.qmuiteam.qmui.widget.dialog.QMUIDialog

class UserInfoActivity : BaseActivity<ActivityUserinfoBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_userinfo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.activity = this
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

    fun onHeadItemClick(view: View) {}
    fun onUUIDItemClick(view: View) {}
    fun onNiceNameItemClick(view: View) {
        QMUIDialog.EditTextDialogBuilder(this)
                .setTitle("请输入新昵称")
                .addAction("取消") { dialog, _ -> dialog.dismiss() }
                .addAction("确定") { dialog, _ -> dialog.dismiss() }
                .create().show()
    }

    fun onSexItemClick(view: View) {}
    fun onAgreementItemClick(view: View) {}
    fun onLogOutItemClick(view: View) {

        QMUIDialog.MessageDialogBuilder(this)
                .setMessage("确定不是手滑了吗？")
                .addAction("取消") { dialog, _ -> dialog.dismiss() }
                .addAction("确定") { dialog, _ -> dialog.dismiss() }
                .create().show()

    }

}