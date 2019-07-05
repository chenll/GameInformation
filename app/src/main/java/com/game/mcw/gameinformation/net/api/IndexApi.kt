package com.game.mcw.gameinformation.net.api

import com.game.mcw.gameinformation.modle.AppResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*


/**
 * Created by sky on 18/1/24.
 */
interface IndexApi {

    @GET("/index/list/game")
    fun getGameList(@Query("page") pageNum: Int): Observable<AppResponse<Any>>

    @GET("/index/list/newsInit")
    fun getNewsList(@Query("page") pageNum: Int): Observable<AppResponse<Any>>

    /**
     * 获取游戏详情
     */
    @GET("/index/detail")
    fun getGameDetils(@Query("id") id: Int): Observable<AppResponse<Any>>

    @GET("/index/list/video")
    fun getVideoList(@Query("page") pageNum: Int): Observable<AppResponse<Any>>

    @GET("/gift/list/recentGift")
    fun getGiftList(@Query("page") pageNum: Int, @Query("limit") limit: Int = 20): Observable<AppResponse<Any>>

    /**
     * 获取独家礼包详情
     */
    @GET("/gift/list/getByGameId")
    fun getByGameId(@Query("gameId") gameId: Int): Observable<AppResponse<Any>>

    /**
     * 独家礼包
     */
    @GET("/gift/list/exclusiveGift")
    fun getExclusiveGift(): Observable<AppResponse<Any>>

    /**
     * 领取礼包
     */
    @GET("/gift/gift/takeGift")
    fun takeGift(@Query("giftId") giftId: Int): Observable<AppResponse<Any>>

    /**
     * 首页资源 包括banner 弹窗 开屏
     */
    @GET("/index/init")
    fun getInit(): Observable<AppResponse<Any>>

    /**
     * 首页资源 包括banner 弹窗 开屏
     */
    @Multipart
    @POST("/file/upload")
    fun uploadFile(@Part body: MultipartBody.Part): Observable<AppResponse<Any>>


    /**
     * 任务列表
     */
    @GET("/center/task/list/taskList")
    fun getTaskList(@Query("page") pageNum: Int, @Query("limit") limit: Int): Observable<AppResponse<Any>>

    /**
     * 商城商品列表
     */
    @GET("/center/user/market/commodityList")
    fun getCommodityList(@Query("page") pageNum: Int, @Query("limit") limit: Int): Observable<AppResponse<Any>>
}