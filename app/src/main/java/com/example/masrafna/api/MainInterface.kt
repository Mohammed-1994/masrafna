package com.example.masrafna.api

import com.example.masrafna.data.auth.request.*
import com.example.masrafna.data.auth.response.*
import com.example.masrafna.data.auth.response.ResetPasswordResponse
import io.reactivex.Single
import retrofit2.http.*

interface MainInterface {

    /**** Auth methods Start ***/

    @POST("auth/signup")
    fun signup(
        @Body signUpModel: SignUpModel,
    ): Single<SignupResponse>


    @POST("auth/signup/verify_otp")
    fun verifyOTP(
        @Body otp: VerifyOTPModel
    ): Single<VerifyOTPResponse>

    @GET("auth/signup/resend_otp")
    fun resendOTPCode(
    ): Single<VerifyOTPResponse>


    @POST("auth/login")
    fun login(
        @Body loginModel: LoginModel,
    ): Single<LoginResponse>


    @POST("auth/logout")
    fun logout(
    ): Single<LogoutResponse>


    @POST("auth/password/request")
    fun resetPassword(
        @Body phone: ChangPassword
    ): Single<ResetPasswordResponse>


    @POST("auth/password/verify_otp")
    fun resetPasswordVerifyOTP(
        @Body changPassVerifyOTP: ChangPassVerifyOTP
    ): Single<ResetPasswordVerifyOTP>

  @POST("auth/password/resend_otp")
    fun resetPasswordResendVerifyOTP(
        @Body phone: ChangPassword
    ): Single<ResetPasswordVerifyOTP>


    /****  End Auth methods  ***/

}