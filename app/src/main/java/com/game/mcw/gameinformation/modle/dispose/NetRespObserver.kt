package com.game.mcw.gameinformation.modle.dispose

import android.text.TextUtils
import android.widget.Toast
import com.game.mcw.gameinformation.MyApplication
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class NetRespObserver<T> : Observer<T> {
    override fun onComplete() {
        onFinish()
    }

    override fun onSubscribe(d: Disposable) {
        onFinish()
    }

    override fun onError(e: Throwable) {
        onFinish()
        Toast.makeText(MyApplication.INSTANCE, "${if (TextUtils.isEmpty(e.message)) "请求出现错误" else e.message}", Toast.LENGTH_SHORT).show()
    }


    open fun onFinish() {


    }

}