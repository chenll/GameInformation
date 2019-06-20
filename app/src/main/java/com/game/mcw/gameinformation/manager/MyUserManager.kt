package com.game.mcw.gameinformation.manager

import android.widget.Toast
import com.game.mcw.gameinformation.MyApplication
import com.game.mcw.gameinformation.event.UserChangeEvent
import com.game.mcw.gameinformation.modle.UserBean
import com.game.mcw.gameinformation.modle.dispose.NetRespObserver
import com.game.mcw.gameinformation.net.AppRepository
import org.greenrobot.eventbus.EventBus
import org.litepal.LitePal

class MyUserManager private constructor() {

    var userBean: UserBean? = null

    companion object {
        val instance: MyUserManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MyUserManager()
        }
    }

    init {
        userBean = LitePal.findFirst(UserBean::class.java)
    }


    fun updateUser(userBean: UserBean?) {
        this.userBean = userBean
        LitePal.deleteAll(UserBean::class.java)
        userBean?.saveOrUpdate("userId=?", userBean.userId.toString())
        EventBus.getDefault().post(UserChangeEvent())
    }

    fun autoAsyncUpdateUserMessage(isManual: Boolean = false) {
        if (userBean == null) {
            if (isManual) {
                Toast.makeText(MyApplication.INSTANCE, "用户信息同步失败", Toast.LENGTH_SHORT).show()
            }
            return
        }
        userBean?.let {
            AppRepository.getUserRepository().getUserMessage().subscribe(object : NetRespObserver<UserBean>() {
                override fun onNext(t: UserBean) {
                    if (it != t) {
                        t.token = it.token
                        updateUser(t)
                    }
                }

                override fun onError(e: Throwable) {
                    if (isManual) {
                        Toast.makeText(MyApplication.INSTANCE, "用户信息同步失败", Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }
    }
}