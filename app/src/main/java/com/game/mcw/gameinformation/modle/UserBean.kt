package com.game.mcw.gameinformation.modle

import com.google.gson.annotations.SerializedName
import org.litepal.crud.LitePalSupport


class UserBean(@SerializedName("id") val userId: Int, val avatar: String, val mobile: String, val nickname: String, val sex: String, val token: String, val totalScore: Int) : LitePalSupport()
