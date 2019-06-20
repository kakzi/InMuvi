package com.studio.karya.inmuvi.ui.movie

import androidx.lifecycle.ViewModel
import com.studio.karya.inmuvi.data.ContentEntity
import com.studio.karya.inmuvi.utils.Data

class MovieViewModel : ViewModel() {

    fun getMovie(): MutableList<ContentEntity> {
        return Data().generateMovie()
    }
}