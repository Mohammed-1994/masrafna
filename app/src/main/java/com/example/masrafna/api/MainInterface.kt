package com.example.masrafna.api

import com.example.masrafna.data.AboutCondeitionsResponse
import com.example.masrafna.data.auth.request.*
import com.example.masrafna.data.auth.response.*
import com.example.masrafna.data.auth.response.ResetPasswordResponse
import com.example.masrafna.data.models.NotificationModel
import com.example.masrafna.data.profile.body.UpdatePassword
import com.example.masrafna.data.profile.body.UpdateProfileBody
import com.example.masrafna.data.profile.response.ProfileResponse
import com.example.masrafna.data.profile.response.UpdatePassResponse
import io.reactivex.Single
import okhttp3.RequestBody
import okhttp3.ResponseBody
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
    fun resendOTPCode(): Single<VerifyOTPResponse>


    @POST("auth/login")
    fun login(
        @Body loginBody: LoginBody,
    ): Single<LoginResponse>


    @POST("auth/logout")
    fun logout(): Single<LogoutResponse>


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


    @GET("profile")
    fun getProfile(

    ): Single<ProfileResponse>

    /****  End Auth methods  ***/


    /****  Start Profile methods  ***/

    @POST("profile")
    fun updateProfileBody(
        @Body profile: RequestBody
    ): Single<ProfileResponse>


    @POST("password/update")
    fun updatePassword(
        @Body updatePassword: UpdatePassword
    ): Single<UpdatePassResponse>


    @GET("notifications")
    fun getNotifications(): Single<NotificationModel>


    /****  End Profile methods  ***/


    /****  About/Conditions methods  ***/

    @GET("conditions")
    fun getConditions(): Single<AboutCondeitionsResponse>

    @GET("about")
    fun getInfo(): Single<AboutCondeitionsResponse>


}