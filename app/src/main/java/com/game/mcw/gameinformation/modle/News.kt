package com.game.mcw.gameinformation.modle

import com.chad.library.adapter.base.entity.MultiItemEntity


class News(val id: Int, val author: String, val description: String, val img: String, val title: String) : MultiItemEntity {

    companion object {
        const val type_1 = 1
        const val type_2 = 2
        const val type_3 = 3
        const val type_4 = 4

    }

    override fun getItemType(): Int {
        return if (id % 2 == 0) {
            type_1
        } else {
            type_2
        }
    }
}