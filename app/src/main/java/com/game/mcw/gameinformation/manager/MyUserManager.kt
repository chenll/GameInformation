package com.game.mcw.gameinformation.manager

import com.game.mcw.gameinformation.modle.UserBean
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


    fun updateUser(userBean: UserBean) {
        this.userBean = userBean
        LitePal.deleteAll(UserBean::class.java)
        userBean.saveOrUpdate("userId=?", userBean.userId.toString())
    }
}