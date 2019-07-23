package com.studio.karya.inmuvi.ui.content.movie

import com.studio.karya.inmuvi.data.source.remote.response.Movie

interface MovieCallback {
    fun setTitleMovie(movie: MutableList<Movie>)
}