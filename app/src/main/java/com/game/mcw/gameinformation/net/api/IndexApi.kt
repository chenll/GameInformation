package com.game.mcw.gameinformation.net.api

import com.game.mcw.gameinformation.modle.AppResponse
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Created by sky on 18/1/24.
 */
interface IndexApi {

    @GET("/index/list/game")
    fun getGameList(@Query("page") pageNum: Int): Observable<AppResponse<Any>>

    @GET("/index/list/news")
    fun getNewsList(@Query("page") pageNum: Int): Observable<AppResponse<Any>>

    @GET("/index/list/video")
    fun getVideoList(@Query("page") pageNum: Int): Observable<AppResponse<Any>>


}