package com.game.mcw.gameinformation.modle

import android.os.Parcel
import android.os.Parcelable


class GameExclusiveGift(val count: Int, val icon: String?, val id: Int, val name: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(count)
        parcel.writeString(icon)
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameExclusiveGift> {
        override fun createFromParcel(parcel: Parcel): GameExclusiveGift {
            return GameExclusiveGift(parcel)
        }

        override fun newArray(size: Int): Array<GameExclusiveGift?> {
            return arrayOfNulls(size)
        }
    }

}