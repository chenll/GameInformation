package com.game.mcw.gameinformation.modle

import com.chad.library.adapter.base.entity.MultiItemEntity


class NewsGroup(val type: Int, val title: String, val news: List<News>) : MultiItemEntity {


    override fun getItemType(): Int {
        return type
    }
}