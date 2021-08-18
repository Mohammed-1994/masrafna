package com.example.masrafna.util

import com.example.masrafna.data.auth.response.LoginResponse
import com.example.masrafna.data.auth.response.SignupResponse
import com.example.masrafna.data.auth.response.VerifyOTPResponse

class Session {
    companion object{
        lateinit var signupResponse: SignupResponse
        lateinit var verifyOTPResponse: VerifyOTPResponse
        lateinit var loginResponse: LoginResponse
        var token = ""
    }
}