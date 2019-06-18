package com.game.mcw.gameinformation.modle.dispose

import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class NetRespObserver<T> : Observer<T> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {


    }

    override fun onError(e: Throwable) {

    }

}