package com.example.masrafna.ui.services.localization

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.masrafna.api.MainInterface
import com.example.masrafna.api.NetworkStatus
import com.example.masrafna.data.models.Localizations
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

private const val TAG = "LocalRepo myTag"

class LocalRepo(
    private val apiService: MainInterface,
    private val compositeDisposable: CompositeDisposable
) {

    private val _localizationResponse = MutableLiveData<Localizations?>()
    val localizationResponse: LiveData<Localizations?> get() = _localizationResponse

    private val _networkState = MutableLiveData<NetworkStatus>()
    val networkStats: LiveData<NetworkStatus>
        get() = _networkState


    fun getLocalizations(page: Int) {
        _networkState.postValue(NetworkStatus.LOADING)
        compositeDisposable.add(
            apiService.getLocalizations(page)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        _networkState.postValue(NetworkStatus.LOADED)
                        _localizationResponse.postValue(it)
                    },
                    {
                        _networkState.postValue(NetworkStatus.ERROR)
                        Log.e(TAG, "getLocalizations: Error", it)
                    })
        )
    }

}