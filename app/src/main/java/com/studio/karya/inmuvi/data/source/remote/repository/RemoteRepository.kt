package com.studio.karya.inmuvi.data.source.remote.repository

import android.annotation.SuppressLint
import com.studio.karya.inmuvi.data.source.remote.MyRetrofit
import com.studio.karya.inmuvi.data.source.remote.Service
import com.studio.karya.inmuvi.data.source.remote.response.ContentResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class RemoteRepository {

    fun getListMovie(apiKey: String, callback: RepositoryCallback<ContentResponse>) {
        MyRetrofit
            .createService(Service::class.java)
            .getListMovie(apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onDataLoaded(it)
            }, {
                callback.onDataError(it.localizedMessage)
            })
    }

    fun getListTv(apiKey: String, callback: RepositoryCallback<ContentResponse>) {
        MyRetrofit
            .createService(Service::class.java)
            .getListTv(apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onDataLoaded(it)
            }, {
                callback.onDataError(it.localizedMessage)
            })
    }
}