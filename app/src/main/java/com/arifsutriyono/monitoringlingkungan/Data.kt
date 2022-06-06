package com.arifsutriyono.monitoringlingkungan

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data (
        var temperature:String ?= "",
        var kelembapan:String ?= ""
): Parcelable