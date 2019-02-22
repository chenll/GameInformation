package com.game.mcw.gameinformation.modle.dispose

import android.text.TextUtils
import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class NetRespObserver<T> : Observer<T> {
    override fun onComplete() {
//        LogUtils.logMe("NetRespObserver   onComplete")
//        ToastUtil.setHasnotNet(false)
        Log.e("aaa", "onComplete")

    }

    override fun onSubscribe(d: Disposable) {
//        LogUtils.logMe("NetRespObserver   onSubscribe")
//        ToastUtil.setHasnotNet(false)
        Log.e("aaa", "onSubscribe")

    }

    override fun onError(e: Throwable) {
        Log.e("aaa", "onError->${e.toString()}")
        when {
            !TextUtils.isEmpty(e.message) -> Log.e("aaa", e.message)
            else -> Log.e("aaa", "无错误信息")
        }


//        if (e is AppRespException) {
//            ToastHelper.show(PhoneApp.INSTANCE, e.message ?: "")
//        }

        //如果出现了 403 错误, 认为是 账号被顶, 跳转到
//        if (RegisterAndLoginActivity.jumpt2RegisterAndLoginPage(e.message ?: "")) return


        //如果出现了 没有网络 错误
//        if (RegisterAndLoginActivity.netIsNotAvailable(e.message ?: "")){
//
//            //先将恢复为可以弹窗
//            ToastUtil.setHasnotNet(false)
//
//            ToastUtil.showToastMsg(PhoneApp.INSTANCE.getString(R.string.str_no_net))
//
//            ToastUtil.setHasnotNet(true)
//
//            return
//        }
    }

}