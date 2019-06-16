package com.game.mcw.gameinformation.net

import com.game.mcw.gameinformation.modle.UserBean
import com.game.mcw.gameinformation.modle.dispose.RepositoryUtils
import com.game.mcw.gameinformation.net.api.UserApi
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable

class UserRepository(userApi: UserApi) {
    private var mUserApi: UserApi = userApi
    fun login(mobile: String,code: String): Observable<UserBean> = RepositoryUtils.extractData(mUserApi.userLogin(mobile,code), object : TypeToken<UserBean>() {}.type)

}