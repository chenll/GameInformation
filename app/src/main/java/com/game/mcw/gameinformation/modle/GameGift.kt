package com.game.mcw.gameinformation.modle

import android.os.Parcel
import android.os.Parcelable


class GameGift(val id: Int, val gameName: String?, val icon: String?, val name: String?, val startTime: String?, val endTime: String?, var code: String?, val leftCount: String?, val takeMethod: String?, val content: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(gameName)
        parcel.writeString(icon)
        parcel.writeString(name)
        parcel.writeString(startTime)
        parcel.writeString(endTime)
        parcel.writeString(code)
        parcel.writeString(leftCount)
        parcel.writeString(takeMethod)
        parcel.writeString(content)
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