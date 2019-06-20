package com.game.mcw.gameinformation.modle

import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import org.litepal.crud.LitePalSupport


class UserBean(@SerializedName("id") val userId: Int, val avatar: String, val mobile: String, val nickname: String, val sex: String?, var token: String, val totalScore: Int) : LitePalSupport() {
    override fun equals(other: Any?): Boolean {

        if (super.equals(other)) {
            return true
        }
        if (other !is UserBean) {
            return false
        }

        return userId == other.userId && avatar == other.avatar && mobile == other.mobile && nickname == other.nickname && sex == other.sex && totalScore == other.totalScore

    }

    fun getSexName(): String {
        return when (sex) {
            "1.0" -> "男"
            "2.0" -> "女"
            else -> "保密"
        }
    }
}
