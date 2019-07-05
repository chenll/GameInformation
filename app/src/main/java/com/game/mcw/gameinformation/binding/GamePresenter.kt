package com.game.mcw.gameinformation.binding

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import com.game.mcw.gameinformation.GameDetailActivity
import com.game.mcw.gameinformation.WebActivity
import com.game.mcw.gameinformation.WebPageActivity
import com.game.mcw.gameinformation.modle.News

class GamePresenter {
    fun onGameClick(view: View, news: News) {
//        WebActivity.goWeb(view.context, news.url, news.name)
//        WebPageActivity.goWeb(view.context, news.url, news.name)
//        WebPageActivity.goWeb(view.context, "https://wap.baidu.com/", news.name)

        view.context.startActivity(Intent(view.context, GameDetailActivity::class.java).apply { putExtra("gameid", news.id) })


    }
}