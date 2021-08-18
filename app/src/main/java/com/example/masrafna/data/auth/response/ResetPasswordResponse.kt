package com.example.masrafna.data.auth.response


import com.google.gson.annotations.SerializedName

data class ResetPasswordResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("payload")
    val payload: Any
)