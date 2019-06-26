package com.game.mcw.gameinformation.modle

import android.os.Parcel
import android.os.Parcelable


/**
 * 领取状态：0：未领取，1：已领取
 */
class GameGift(val id: Int, val gameName: String?, val icon: String?, val url: String?, val name: String?, val startTime: String?, val endTime: String?, var code: String?, val leftCount: Int, val total: Int, val takeMethod: String?, val content: String?,var status:Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(gameName)
        parcel.writeString(icon)
        parcel.writeString(url)
        parcel.writeString(name)
        parcel.writeString(startTime)
        parcel.writeString(endTime)
        parcel.writeString(code)
        parcel.writeInt(leftCount)
        parcel.writeInt(total)
        parcel.writeString(takeMethod)
        parcel.writeString(content)
        parcel.writeInt(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameGift> {
        override fun createFromParcel(parcel: Parcel): GameGift {
            return GameGift(parcel)
        }

        override fun newArray(size: Int): Array<GameGift?> {
            return arrayOfNulls(size)
        }
    }


}