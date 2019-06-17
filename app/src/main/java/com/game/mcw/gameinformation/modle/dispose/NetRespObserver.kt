package com.game.mcw.gameinformation.modle.dispose

import android.text.TextUtils
import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class NetRespObserver<T> : Observer<T> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {


    }

    override fun onError(e: Throwable) {
        Log.e("aaa", "onError->${e.toString()}")
        when {
            !TextUtils.isEmpty(e.message) -> Log.e("aaa", e.message)
            else -> Log.e("aaa", "无错误信息")
        }

    }

}