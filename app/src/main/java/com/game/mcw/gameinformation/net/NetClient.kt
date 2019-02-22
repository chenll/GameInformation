package com.game.mcw.gameinformation.net

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.game.mcw.gameinformation.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class NetClient(val ctx: Context) {

    private var retrofit: Retrofit

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor { chain ->
                    val original = chain.request().newBuilder().addHeader("device", "android")
//                    val token = KeyValueCache.getToken()
//                    if (!TextUtils.isEmpty(token)) {
//                        original.addHeader("X-Auth-Token", token)
//                        Logger.d("token $token")
//                    }
                    val request = original.build()
                    chain.proceed(request.newBuilder().method(request.method(), request.body()).build())
                }
                .build()
        retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
    }


    fun <T> createApi(clazz: Class<T>): T = retrofit.create(clazz)
}