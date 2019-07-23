package com.studio.karya.inmuvi.ui.content

import com.studio.karya.inmuvi.data.source.remote.response.Content
import com.studio.karya.inmuvi.data.source.remote.response.ContentResponse
import com.studio.karya.inmuvi.data.source.remote.response.Tv

interface ContentCallback {
    fun setTitleContent(content: MutableList<Content>)
}