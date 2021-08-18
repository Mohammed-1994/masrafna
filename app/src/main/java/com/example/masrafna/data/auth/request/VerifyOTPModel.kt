package com.example.masrafna.data.auth.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class VerifyOTPModel(
    var otp: String? = null,
): Parcelable