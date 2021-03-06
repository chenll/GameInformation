package com.game.mcw.gameinformation

import android.content.Intent
import android.support.v4.app.Fragment
import org.greenrobot.eventbus.EventBus

open class BaseFragment : Fragment() {

    fun startActivity(cls: Class<*>) {
        super.startActivity(Intent(activity, cls))
    }

    fun registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        super.onDestroy()

    }

    fun showLoading() {
        if (activity is BaseActivity<*>) {
            (activity as BaseActivity<*>).showLoading()
        }

    }


    fun hideLoading() {
        if (activity is BaseActivity<*>) {
            (activity as BaseActivity<*>).hideLoading()
        }
    }

}