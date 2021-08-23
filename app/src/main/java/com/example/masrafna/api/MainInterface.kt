package com.example.masrafna.api

import com.example.masrafna.data.auth.request.*
import com.example.masrafna.data.auth.response.*
import com.example.masrafna.data.auth.response.ResetPasswordResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface MainInterface {

    /**** Auth methods Start ***/

    @POST("auth/signup")
    fun signup(
        @Body signUpBody: SignUpBody,
    ): Single<SignupResponse>


    @POST("auth/signup/verify_otp")
    fun verifyOTP(
        @Body otp: VerifyOTPBody
    ): Single<VerifyOTPResponse>

    @GET("auth/signup/resend_otp")
    fun resendOTPCode(
    ): Single<VerifyOTPResponse>


    @POST("auth/login")
    fun login(
        @Body loginBody: LoginBody,
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


  @POST("auth/change_password")
    fun changePassword(
        @Body password: ChagnePasswordBody
    ): Single<ChangePasswordResponse>

    /****  End Auth methods  ***/

}