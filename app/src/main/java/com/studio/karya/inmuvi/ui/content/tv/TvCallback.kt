package com.studio.karya.inmuvi.ui.content.tv

import com.studio.karya.inmuvi.data.source.remote.response.Tv

interface TvCallback {
    fun setTitleTv(tv: MutableList<Tv>)
}