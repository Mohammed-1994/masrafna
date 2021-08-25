package com.example.masrafna.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class City(
    var id: Int = 0,
    var name: String? = null,
) : Parcelable
