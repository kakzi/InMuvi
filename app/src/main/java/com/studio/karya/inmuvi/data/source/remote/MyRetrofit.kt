package com.studio.karya.inmuvi.data.source.remote

import com.studio.karya.inmuvi.BuildConfig.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofit {

    private fun retrofit(): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL).build()
    }

    fun <T> createService(service: Class<T>): T {
        return retrofit().create(service)
    }
}