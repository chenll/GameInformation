package com.game.mcw.gameinformation.modle

import com.chad.library.adapter.base.entity.MultiItemEntity


class NewsGroup(val type: Int, val name: String, val data: List<News>) : MultiItemEntity {


    override fun getItemType(): Int {
        return type
    }
}