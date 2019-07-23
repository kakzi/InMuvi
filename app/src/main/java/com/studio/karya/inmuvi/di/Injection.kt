package com.studio.karya.inmuvi.di

import android.app.Application
import com.studio.karya.inmuvi.data.source.ContentRepository
import com.studio.karya.inmuvi.data.source.remote.repository.RemoteRepository

class Injection {

    fun provideRepository(application: Application): ContentRepository? {
        val remoteRepository = RemoteRepository()
        return ContentRepository().getInstance(remoteRepository)
    }
}