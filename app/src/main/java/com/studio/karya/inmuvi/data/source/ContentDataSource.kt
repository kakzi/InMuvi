package com.studio.karya.inmuvi.data.source

import androidx.lifecycle.LiveData
import com.studio.karya.inmuvi.data.source.remote.response.MovieResponse
import com.studio.karya.inmuvi.data.source.remote.response.TvResponse

interface ContentDataSource {

    fun getListMovie(): LiveData<MovieResponse>
    fun getListTv(): LiveData<TvResponse>
}