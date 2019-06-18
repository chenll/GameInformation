package com.game.mcw.gameinformation

import android.app.Application
import android.support.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.game.mcw.gameinformation.net.AppRepository
import org.litepal.LitePal

class MyApplication : Application() {

    companion object {
        lateinit var INSTANCE: MyApplication

    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        INSTANCE = this
        Stetho.initializeWithDefaults(this)
        AppRepository.initialize(this)
        LitePal.initialize(this)

    }
}