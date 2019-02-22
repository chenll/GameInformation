package com.game.mcw.gameinformation.modle

import com.google.gson.annotations.SerializedName

/**
 * Created by sky on 18/2/24.
 */
data class PageList<T>(@SerializedName("list") val list: List<T>,
                       @SerializedName("all") val all: Int,
                       @SerializedName("pages") val pages: Int,
                       @SerializedName("size") val size: Int,
                       @SerializedName("total") val total: Int,
                       @SerializedName("number") val number: Int)
