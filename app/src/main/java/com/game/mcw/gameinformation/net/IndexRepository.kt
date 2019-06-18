package com.game.mcw.gameinformation.net

import android.util.Log
import com.game.mcw.gameinformation.modle.*
import com.game.mcw.gameinformation.modle.dispose.RepositoryUtils
import com.game.mcw.gameinformation.net.api.IndexApi
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class IndexRepository(indexApi: IndexApi) {
    private var mIndexApi: IndexApi = indexApi
    fun getGameList(page: Int): Observable<List<Game>> = RepositoryUtils.extractData(mIndexApi.getGameList(page), object : TypeToken<List<Game>>() {}.type)
    fun getNewsList(page: Int): Observable<List<NewsGroup>> = RepositoryUtils.extractData(mIndexApi.getNewsList(page), object : TypeToken<List<NewsGroup>>() {}.type)
    fun getVideoList(page: Int): Observable<List<Video>> = RepositoryUtils.extractData(mIndexApi.getVideoList(page), object : TypeToken<List<Video>>() {}.type)
    fun getGameGiftList(page: Int): Observable<List<GameGift>> = RepositoryUtils.extractData(mIndexApi.getGiftList(page), object : TypeToken<List<GameGift>>() {}.type)
    fun getByGameId(gameId: Int): Observable<GameExclusiveGiftDetail> = RepositoryUtils.extractData(mIndexApi.getByGameId(gameId), object : TypeToken<GameExclusiveGiftDetail>() {}.type)
    fun getGameExclusiveGiftList(): Observable<List<GameExclusiveGift>> = RepositoryUtils.extractData(mIndexApi.getExclusiveGift(), object : TypeToken<List<GameExclusiveGift>>() {}.type)
    fun getInit(): Observable<IndexResource> = RepositoryUtils.extractData(mIndexApi.getInit(), object : TypeToken<IndexResource>() {}.type)


    fun takeGift(giftId: Int): Observable<String> = RepositoryUtils.extractData(mIndexApi.takeGift(giftId), object : TypeToken<String>() {}.type)


    fun upLoadFile(path: String): Observable<String> {
        Log.e("aaa", "图片地址:$path")
        val file = File(path)
        val fileRQ = RequestBody.create(MediaType.parse("image/*"), file)
        return RepositoryUtils.extractData(mIndexApi.uploadFile(MultipartBody.Part.createFormData("file", file.name, fileRQ)), object : TypeToken<String>() {}.type)
    }
}