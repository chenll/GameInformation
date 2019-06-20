package com.game.mcw.gameinformation.net

import com.game.mcw.gameinformation.manager.MyUserManager
import com.game.mcw.gameinformation.modle.UserBean
import com.game.mcw.gameinformation.modle.VcodeResponse
import com.game.mcw.gameinformation.modle.dispose.RepositoryUtils
import com.game.mcw.gameinformation.net.api.UserApi
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable

class UserRepository(userApi: UserApi) {
    private var mUserApi: UserApi = userApi
    fun login(mobile: String, code: String): Observable<UserBean> = RepositoryUtils.extractData(mUserApi.userLogin(mobile, code), object : TypeToken<UserBean>() {}.type)
    fun getVcode4Login(mobile: String): Observable<VcodeResponse> = RepositoryUtils.extractDataSimple(mUserApi.getVcode(mobile, 1), VcodeResponse::class.java)
    fun getVcode4Regist(mobile: String): Observable<VcodeResponse> = RepositoryUtils.extractDataSimple(mUserApi.getVcode(mobile, 2), VcodeResponse::class.java)


    //    fun editUserMessage(avatar: String = MyUserManager.instance.userBean!!.avatar, nickname: String = MyUserManager.instance.userBean!!.nickname, sex: String = MyUserManager.instance.userBean!!.sex): Observable<UserBean> = RepositoryUtils.extractData(mUserApi.editUserMessage(avatar, nickname, sex), UserBean::class.java)
    fun editUserMessage(avatar: String = MyUserManager.instance.userBean!!.avatar, nickname: String = MyUserManager.instance.userBean!!.nickname, sex: String = "1"): Observable<String> = RepositoryUtils.extractData(mUserApi.editUserMessage(avatar, nickname, sex), String::class.java)


    fun getUserMessage(): Observable<UserBean> = RepositoryUtils.extractData(mUserApi.getUserMessage(), object : TypeToken<UserBean>() {}.type)

}