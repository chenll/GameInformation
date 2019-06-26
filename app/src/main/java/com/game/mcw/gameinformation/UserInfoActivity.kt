package com.game.mcw.gameinformation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.game.mcw.gameinformation.databinding.ActivityUserinfoBinding
import com.game.mcw.gameinformation.dialog.NickNameEditDialog
import com.game.mcw.gameinformation.dialog.SexSelectDialog
import com.game.mcw.gameinformation.event.UserChangeEvent
import com.game.mcw.gameinformation.manager.MyUserManager
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.game.mcw.gameinformation.utils.GlideUtil
import com.game.mcw.gameinformation.utils.GlideV4ImageEngine
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.tbruyelle.rxpermissions2.RxPermissions
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class UserInfoActivity : BaseActivity<ActivityUserinfoBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_userinfo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.activity = this
        initToolBar()
        mBinding.user = MyUserManager.instance.userBean
        MyUserManager.instance.userBean?.avatar?.let { GlideUtil.loadCircleHeadPic(it, mBinding.ivHead) }
        registerEventBus()
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
        mBinding.includeToolbar.toolbarTitle.text = "个人资料"
    }

    override fun initStatusBar() {
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("CheckResult")
    fun onHeadItemClick(view: View) {
        RxPermissions(this).request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe {
            if (it) {
                Matisse.from(this@UserInfoActivity)
                        .choose(MimeType.ofImage())
                        .showSingleMediaType(true)
                        .capture(true)
                        .captureStrategy(CaptureStrategy(true, "PhotoPicker"))
                        .countable(true).maxSelectable(1)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .thumbnailScale(0.8f)
                        .imageEngine(GlideV4ImageEngine())
                        .forResult(111)
            } else {

            }

        }


    }

    fun onUUIDItemClick(view: View) {}
    fun onNiceNameItemClick(view: View) {
        NickNameEditDialog().apply {
            show(this@UserInfoActivity.supportFragmentManager, "nicknameeditdialog")
        }
    }

    fun onSexItemClick(view: View) {
        SexSelectDialog().apply {
            show(this@UserInfoActivity.supportFragmentManager, "sexselectdialog")
        }

    }

    fun onAgreementItemClick(view: View) {}
    fun onLogOutItemClick(view: View) {

        QMUIDialog.MessageDialogBuilder(this)
                .setMessage("确定不是手滑了吗？")
                .addAction("取消") { dialog, _ -> dialog.dismiss() }
                .addAction("确定") { dialog, _ ->
                    showLoading()
                    AppRepository.getUserRepository().logout().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : NetRespObserver<String>() {
                        override fun onNext(data: String) {
                            hideLoading()
                            MyUserManager.instance.updateUser(null)
                            dialog.dismiss()
                            finish()
                        }

                        override fun onError(e: Throwable) {
                            super.onError(e)
                            dialog.dismiss()
                            hideLoading()
                        }

                    })
                }.create().show()

    }

    @SuppressLint("CheckResult")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            //图片路径 同样视频地址也是这个 根据requestCode
//            val pathList = Matisse.obtainResult(data)
            val pathList = Matisse.obtainPathResult(data)
            showLoading()
            AppRepository.getIndexRepository().upLoadFile(pathList[0])
                    .subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                    .flatMap(Function<String, Observable<String>> { headUrl ->
                        return@Function AppRepository.getUserRepository().editUserMessage(avatar = headUrl)
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : NetRespObserver<String>() {
                        override fun onNext(user: String) {
                            hideLoading()
//                            MyUserManager.instance.updateUser(user)
                            Toast.makeText(MyApplication.INSTANCE, "修改成功", Toast.LENGTH_SHORT).show()
                            MyUserManager.instance.autoAsyncUpdateUserMessage(true)

                        }

                        override fun onError(e: Throwable) {
                            super.onError(e)
                            hideLoading()
                        }
                    })

        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun Event(userChangeEvent: UserChangeEvent) {
        mBinding.user = MyUserManager.instance.userBean
        MyUserManager.instance.userBean?.avatar?.let { GlideUtil.loadCircleHeadPic(it, mBinding.ivHead) }
    }


}