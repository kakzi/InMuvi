package com.studio.karya.inmuvi.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.studio.karya.inmuvi.data.source.ContentRepository
import com.studio.karya.inmuvi.di.Injection
import com.studio.karya.inmuvi.ui.content.ContentViewModel


@Suppress("UNCHECKED_CAST")
class ViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    @Volatile
    private lateinit var instance: ViewModelFactory

    private lateinit var contentRepository: ContentRepository

    private constructor(contentRepository: ContentRepository) : this() {
        this.contentRepository = contentRepository
    }

    fun getInstance(application: Application): ViewModelFactory {
        synchronized(ViewModelFactory::class.java) {
            val injection = Injection().provideRepository(application)
            if (injection != null) {
                instance = ViewModelFactory(injection)
            }
        }
        return instance
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ContentViewModel::class.java) -> ContentViewModel(contentRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}