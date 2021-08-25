package com.example.masrafna.api

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED,
    NO_MORE,
    WRONG_CREDENTIALS,
    ATTEMPTS_EXCEEDED,
    INVALID_OTP,
    CODE_422,
    CODE_404,
    CODE_401
}

class NetworkStatus(val status: Status, val msg: String) {
    companion object {
        val LOADED: NetworkStatus = NetworkStatus(Status.SUCCESS, "Success")
        val LOADING: NetworkStatus = NetworkStatus(Status.RUNNING, "Running")
        val ERROR: NetworkStatus = NetworkStatus(Status.FAILED, "Failed")
    }
}