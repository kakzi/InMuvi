package com.studio.karya.inmuvi.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.studio.karya.inmuvi.BuildConfig.API_KEY
import com.studio.karya.inmuvi.data.source.remote.repository.RemoteRepository
import com.studio.karya.inmuvi.data.source.remote.repository.RepositoryCallback
import com.studio.karya.inmuvi.data.source.remote.response.ContentResponse
import com.studio.karya.inmuvi.data.source.remote.response.MovieResponse
import com.studio.karya.inmuvi.data.source.remote.response.TvResponse

class ContentRepository() : ContentDataSource {

    private lateinit var remoteRepository: RemoteRepository

    private constructor(remoteRepository: RemoteRepository) : this() {
        this.remoteRepository = remoteRepository
    }

    fun getInstance(remoteRepository: RemoteRepository): ContentRepository? {
        var instance: ContentRepository? = null
        if (instance == null) {
            synchronized(ContentRepository::class.java) {
                instance = ContentRepository(remoteRepository)
            }
        }
        return instance
    }

    override fun getListMovie(): LiveData<ContentResponse> {
        val movieResult: MutableLiveData<ContentResponse> = MutableLiveData()
        remoteRepository.getListMovie(API_KEY, object: RepositoryCallback<ContentResponse>{
            override fun onDataLoaded(data: ContentResponse) {
                movieResult.postValue(data)
            }

            override fun onDataError(errorMessage: String) {
            }
        })
        return movieResult
    }

    override fun getListTv(): LiveData<ContentResponse> {
        val tvResult: MutableLiveData<ContentResponse> = MutableLiveData()
        remoteRepository.getListTv(API_KEY, object : RepositoryCallback<ContentResponse>{
            override fun onDataLoaded(data: ContentResponse) {
                tvResult.postValue(data)
            }

            override fun onDataError(errorMessage: String) {
            }
        })
        return tvResult
    }
}