package com.studio.karya.inmuvi.ui.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.studio.karya.inmuvi.data.source.ContentRepository
import com.studio.karya.inmuvi.data.source.remote.response.MovieResponse
import com.studio.karya.inmuvi.data.source.remote.response.TvResponse

class ContentViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun getMovie(): LiveData<MovieResponse> {
        return contentRepository.getListMovie()
    }

    fun getTv(): LiveData<TvResponse> {
        return contentRepository.getListTv()
    }
}