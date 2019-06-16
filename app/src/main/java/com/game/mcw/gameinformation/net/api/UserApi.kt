package com.game.mcw.gameinformation.net.api

import com.game.mcw.gameinformation.modle.AppResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Created by sky on 18/1/24.
 */
interface UserApi {
    @FormUrlEncoded
    @POST("/user/login")
    fun userLogin(@Field("mobile") mobile: String, @Field("code") code: String): Observable<AppResponse<Any>>


}