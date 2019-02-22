package com.game.mcw.gameinformation.net

import android.content.Context
import com.game.mcw.gameinformation.net.api.IndexApi
import java.util.concurrent.ConcurrentHashMap

object AppRepository {

    private val sServiceMap: ConcurrentHashMap<Class<*>, Any> = ConcurrentHashMap()

    @JvmStatic
    fun initialize(context: Context) {
        val client = NetClient(context)
        sServiceMap[IndexRepository::class.java] = IndexRepository(client.createApi(IndexApi::class.java))
    }



    @JvmStatic
    fun getIndexRepository() = IndexRepository::class.java.cast(sServiceMap[IndexRepository::class.java])!!

}