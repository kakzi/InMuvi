package com.studio.karya.inmuvi.data.source.remote.repository

interface RepositoryCallback<T> {
    fun onDataLoaded(data: T)
    fun onDataError(errorMessage: String)
}