package com.game.mcw.gameinformation

import android.os.Bundle
import android.view.View
import com.game.mcw.gameinformation.databinding.ActivityLoginBinding
import com.game.mcw.gameinformation.modle.UserBean
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.activity = this

    }

    override fun initStatusBar() {
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
    }

    fun click(v: View) {
        AppRepository.getUserRepository().login("15651825290", "12").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : NetRespObserver<UserBean>() {
            override fun onNext(data: UserBean) {


            }


        })
    }
}