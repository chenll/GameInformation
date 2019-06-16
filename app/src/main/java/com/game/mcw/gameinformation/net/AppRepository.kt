package com.game.mcw.gameinformation.net

import android.content.Context
import com.game.mcw.gameinformation.net.api.IndexApi
import com.game.mcw.gameinformation.net.api.UserApi
import java.util.concurrent.ConcurrentHashMap

object AppRepository {

    private val sServiceMap: ConcurrentHashMap<Class<*>, Any> = ConcurrentHashMap()

    @JvmStatic
    fun initialize(context: Context) {
        val client = NetClient(context)
        sServiceMap[IndexRepository::class.java] = IndexRepository(client.createApi(IndexApi::class.java))
        sServiceMap[UserRepository::class.java] = UserRepository(client.createApi(UserApi::class.java))
    }


    @JvmStatic
    fun getIndexRepository() = IndexRepository::class.java.cast(sServiceMap[IndexRepository::class.java])!!

    @JvmStatic
    fun getUserRepository() = UserRepository::class.java.cast(sServiceMap[UserRepository::class.java])!!

}