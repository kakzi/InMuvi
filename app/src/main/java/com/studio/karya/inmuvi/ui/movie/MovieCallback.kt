package com.studio.karya.inmuvi.ui.movie

import com.studio.karya.inmuvi.data.ContentEntity

interface MovieCallback {
    fun listenerContent(content: ContentEntity)
}