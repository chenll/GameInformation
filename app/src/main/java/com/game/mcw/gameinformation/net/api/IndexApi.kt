package com.game.mcw.gameinformation.net.api

import com.game.mcw.gameinformation.modle.AppResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by sky on 18/1/24.
 */
interface IndexApi {

    @GET("/index/list/game")
    fun getGameList(@Query("page") pageNum: Int): Observable<AppResponse<Any>>

    @GET("/index/list/newsInit")
    fun getNewsList(@Query("page") pageNum: Int): Observable<AppResponse<Any>>

    @GET("/index/list/video")
    fun getVideoList(@Query("page") pageNum: Int): Observable<AppResponse<Any>>

    @GET("/gift/list/recentGift")
    fun getGiftList(@Query("page") pageNum: Int, @Query("token") token: Int = 111, @Query("limit") limit: Int = 20): Observable<AppResponse<Any>>

    /**
     * 首页资源 包括banner 弹窗 开屏
     */
    @GET("/index/init")
    fun getInit(): Observable<AppResponse<Any>>


}