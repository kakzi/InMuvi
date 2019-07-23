package com.studio.karya.inmuvi.ui.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.studio.karya.inmuvi.data.source.ContentRepository
import com.studio.karya.inmuvi.data.source.remote.response.ContentResponse

class ContentViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun getMovie(): LiveData<ContentResponse> {
        return contentRepository.getListMovie()
    }

    fun getTv(): LiveData<ContentResponse> {
        return contentRepository.getListTv()
    }

}