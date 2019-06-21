package com.studio.karya.inmuvi.ui.movie

import androidx.lifecycle.ViewModel
import com.studio.karya.inmuvi.data.ContentEntity
import com.studio.karya.inmuvi.utils.Data

class ContentViewModel : ViewModel() {

    fun getMovie(): MutableList<ContentEntity> {
        return Data().generateMovie()
    }

    fun getTv(): MutableList<ContentEntity> {
        return Data().generateTv()
    }
}