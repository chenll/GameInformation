package com.game.mcw.gameinformation

import android.app.Application
import com.facebook.stetho.Stetho
import com.game.mcw.gameinformation.net.AppRepository

class MyApplication : Application() {

    companion object {
        lateinit var INSTANCE: MyApplication

    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        Stetho.initializeWithDefaults(this)
        AppRepository.initialize(this)
    }
}