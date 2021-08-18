package com.example.masrafna.util

import android.util.Log
import androidx.drawerlayout.widget.DrawerLayout
import com.example.masrafna.data.auth.response.LoginResponse
import com.example.masrafna.data.auth.response.SignupResponse
import com.example.masrafna.data.auth.response.VerifyOTPResponse
import com.example.masrafna.databinding.ActivityNavigationDrawerBinding

private const val TAG = "Session myTag"
class Session {
    companion object {
        lateinit var signupResponse: SignupResponse
        lateinit var verifyOTPResponse: VerifyOTPResponse
        lateinit var loginResponse: LoginResponse
        lateinit var navigationDrawerBinding: ActivityNavigationDrawerBinding
        var drawerLayout: DrawerLayout? = null
        var token = ""


        fun setDrawer(drawerLayout: DrawerLayout){
            if (this.drawerLayout == null){
                Log.d(TAG, "setDrawer: ")
                this.drawerLayout = drawerLayout
            }
        }
    }
}