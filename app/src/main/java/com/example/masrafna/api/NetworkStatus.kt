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
    CODE_404
}

class NetworkStatus(val status: Status, val msg: String) {
    companion object {
        val LOADED: NetworkStatus = NetworkStatus(Status.SUCCESS, "Success")
        val LOADING: NetworkStatus = NetworkStatus(Status.RUNNING, "Running")
        val ERROR: NetworkStatus = NetworkStatus(Status.FAILED, "Failed")
        val NUMBER_IS_TAKEN: NetworkStatus = NetworkStatus(Status.NO_MORE, "الرقم المدخل مسجل بالفعل, جرب تسجيل الدخول")
        val WRONG_CREDENTIALS: NetworkStatus = NetworkStatus(Status.WRONG_CREDENTIALS, "الرجاء التأكد من رقم الهاتف وكلمة السر")
        val INVALID_OTP: NetworkStatus = NetworkStatus(Status.INVALID_OTP, "الرجاء التأكد من الرمز السري")
        val ATTEMPTS_EXCEEDED: NetworkStatus = NetworkStatus(Status.ATTEMPTS_EXCEEDED, "لقد تجاوزت الحد المسموج في محاولة تأكيد الحساب, الرجاء الانتظار 4 ساعات")

    }
}