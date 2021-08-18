package com.example.masrafna.data.auth.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SignUpModel(
    var name: String? = null,
    var phone: String? = null,
    var password: String? = null
): Parcelable