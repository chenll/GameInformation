package com.game.mcw.gameinformation.modle.dispose

import android.text.TextUtils
import android.util.Log
import com.game.mcw.gameinformation.utils.GsonUtils
import com.game.mcw.gameinformation.modle.AppResponse
import com.game.mcw.gameinformation.modle.exception.AppRespException
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.functions.Function
import java.lang.reflect.Type


object RepositoryUtils {
    val mGson = GsonBuilder().create()

    private val TAG = "aaa"

    fun <T> extractData(observable: Observable<AppResponse<Any>>, clazz: Class<T>): Observable<T> {
        return observable.flatMap(Function<AppResponse<Any>, Observable<T>> { response ->
            return@Function if (response.code == "0") {
                Log.e(TAG, GsonUtils.toJson(response))
                Observable.just<T>(mGson.fromJson(mGson.toJson(response.data), clazz))
            } else {
                when {
                    !TextUtils.isEmpty(response.msg) -> Log.e(TAG, response.msg)
                    else -> Log.e(TAG, "无错误信息")
                }
                Observable.error(AppRespException(response))
            }

        })
    }

    fun <T> extractData(observable: Observable<AppResponse<Any>>, type: Type): Observable<T> {
        return observable.flatMap(Function<AppResponse<Any>, Observable<T>> { response ->
            return@Function if (response.code == "0") {
                Log.e(TAG, GsonUtils.toJson(response))
                Observable.just<T>(mGson.fromJson(mGson.toJson(response.data), type))
            } else {
                when {
                    !TextUtils.isEmpty(response.msg) -> Log.e(TAG, response.msg)
                    else -> Log.e(TAG, "无错误信息")
                }
                Observable.error(AppRespException(response))
            }

        })
    }


    // 请求洛基服务器数据的响应处理
    fun <T> extractDataRocky(observable: Observable<Any>, clazz: Class<T>): Observable<T> {
        return observable.flatMap(Function<Any, Observable<T>> { response ->
            Log.e(TAG, "extractDataRocky :${response.toString()}")
            return@Function Observable.just<T>(mGson.fromJson(mGson.toJson(response), clazz))

        })
    }

}