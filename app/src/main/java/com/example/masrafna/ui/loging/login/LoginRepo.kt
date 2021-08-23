package com.example.masrafna.ui.loging.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.masrafna.api.MainInterface
import com.example.masrafna.api.NetworkStatus
import com.example.masrafna.api.Status
import com.example.masrafna.data.auth.request.LoginBody
import com.example.masrafna.data.auth.response.LoginResponse
import com.example.masrafna.data.auth.response.SignupResponse
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException


private const val TAG = "LoginRepo myTag"

class LoginRepo(

    private val apiService: MainInterface,
    private val compositeDisposable: CompositeDisposable
) {
    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> get() = _loginResponse

    private val _networkState = MutableLiveData<NetworkStatus>()
    val networkStats: LiveData<NetworkStatus>
        get() = _networkState

    fun login(loginBody: LoginBody) {
        _networkState.postValue(NetworkStatus.LOADING)
        compositeDisposable.add(
            apiService.login(loginBody)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        _loginResponse.postValue(it)
                        _networkState.postValue(NetworkStatus.LOADED)

                    },
                    {
                        if (it is HttpException) {
                            val body = it.response()!!.errorBody()
                            if (it.code() == 422) {
                                val gson = Gson().fromJson(
                                    body?.string(), SignupResponse::class.java
                                )
                                _networkState.postValue(
                                    NetworkStatus(Status.CODE_422, gson.message)
                                )
                            }
                        }
                    })
        )
    }

}