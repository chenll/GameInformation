package com.game.mcw.gameinformation.net

import com.game.mcw.gameinformation.modle.*
import com.game.mcw.gameinformation.modle.dispose.RepositoryUtils
import com.game.mcw.gameinformation.net.api.IndexApi
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable

class IndexRepository(indexApi: IndexApi) {
    private var mIndexApi: IndexApi = indexApi
    fun getGameList(page: Int): Observable<List<Game>> = RepositoryUtils.extractData(mIndexApi.getGameList(page), object : TypeToken<List<Game>>() {}.type)

    fun getNewsList(page: Int): Observable<List<NewsGroup>> = RepositoryUtils.extractData(mIndexApi.getNewsList(page), object : TypeToken<List<NewsGroup>>() {}.type)
    fun getVideoList(page: Int): Observable<List<Video>> = RepositoryUtils.extractData(mIndexApi.getVideoList(page), object : TypeToken<List<Video>>() {}.type)
    fun getGameGiftList(page: Int): Observable<List<GameGift>> = RepositoryUtils.extractData(mIndexApi.getGiftList(page), object : TypeToken<List<GameGift>>() {}.type)
    fun getGameExclusiveGiftList(): Observable<List<GameExclusiveGift>> = RepositoryUtils.extractData(mIndexApi.getExclusiveGift(), object : TypeToken<List<GameExclusiveGift>>() {}.type)
    fun getInit(): Observable<IndexResource> = RepositoryUtils.extractData(mIndexApi.getInit(), object : TypeToken<IndexResource>() {}.type)
}