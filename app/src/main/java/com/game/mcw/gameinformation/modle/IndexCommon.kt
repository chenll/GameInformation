package com.game.mcw.gameinformation.modle

import android.os.Parcel
import android.os.Parcelable
import org.litepal.crud.LitePalSupport


class IndexCommon(val image: String?, val url: String?, val startDate: String?, val endDate: String?) : LitePalSupport(), Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(url)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IndexCommon> {
        override fun createFromParcel(parcel: Parcel): IndexCommon {
            return IndexCommon(parcel)
        }

        override fun newArray(size: Int): Array<IndexCommon?> {
            return arrayOfNulls(size)
        }
    }
}
