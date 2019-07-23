package com.studio.karya.inmuvi.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.studio.karya.inmuvi.BuildConfig.API_KEY
import com.studio.karya.inmuvi.data.source.remote.repository.RemoteRepository
import com.studio.karya.inmuvi.data.source.remote.repository.RepositoryCallback
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

    override fun getListMovie(): LiveData<MovieResponse> {
        val movieResult: MutableLiveData<MovieResponse> = MutableLiveData()
        remoteRepository.getListMovie(API_KEY, object: RepositoryCallback<MovieResponse>{
            override fun onDataLoaded(data: MovieResponse) {
                movieResult.postValue(data)
            }

            override fun onDataError(errorMessage: String) {
            }
        })
        return movieResult
    }

    override fun getListTv(): LiveData<TvResponse> {
        val tvResult: MutableLiveData<TvResponse> = MutableLiveData()
        remoteRepository.getListTv(API_KEY, object : RepositoryCallback<TvResponse>{
            override fun onDataLoaded(data: TvResponse) {
                tvResult.postValue(data)
            }

            override fun onDataError(errorMessage: String) {
            }
        })
        return tvResult
    }
}