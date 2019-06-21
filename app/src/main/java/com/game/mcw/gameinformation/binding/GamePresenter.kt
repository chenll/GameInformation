package com.game.mcw.gameinformation.binding

import android.view.View
import com.game.mcw.gameinformation.WebActivity
import com.game.mcw.gameinformation.modle.News

class GamePresenter {
    fun onGameClick(view: View, news: News) {
        WebActivity.goWeb(view.context, news.url, news.name)
    }
}