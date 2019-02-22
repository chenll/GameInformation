package com.game.mcw.gameinformation.net

import com.game.mcw.gameinformation.modle.Game
import com.game.mcw.gameinformation.modle.News
import com.game.mcw.gameinformation.modle.Video
import com.game.mcw.gameinformation.modle.dispose.RepositoryUtils
import com.game.mcw.gameinformation.net.api.IndexApi
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable

class IndexRepository(indexApi: IndexApi) {
    private var mIndexApi: IndexApi = indexApi
    fun getGameList(page: Int): Observable<List<Game>> = RepositoryUtils.extractData(mIndexApi.getGameList(page), object : TypeToken<List<Game>>() {}.type)

    fun getNewsList(page: Int): Observable<List<News>> = RepositoryUtils.extractData(mIndexApi.getNewsList(page), object : TypeToken<List<News>>() {}.type)
    fun getVideoList(page: Int): Observable<List<Video>> = RepositoryUtils.extractData(mIndexApi.getVideoList(page), object : TypeToken<List<Video>>() {}.type)
}