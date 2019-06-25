package com.game.mcw.gameinformation.modle


class Task(val id: Int, val type: Int, val title: String, val description: String, var score: Int, val status: Int) {


    fun getTaskStatusName(): String {
        return when (status) {
            1 -> "未领取"
            2 -> "已领取"
            3 -> "已完成"
            else -> "位置"
        }
    }
}