package com.studio.karya.inmuvi.ui.detail

import androidx.lifecycle.ViewModel
import com.studio.karya.inmuvi.data.ContentEntity
import com.studio.karya.inmuvi.utils.Data

class DetailViewModel : ViewModel() {

    private lateinit var content: ContentEntity
    private lateinit var contentId: String

    fun getContent(type: String): ContentEntity {
        val contentEntity = if (type == "movie") Data().generateMovie() else Data().generateTv()
        for (i in 0 until contentEntity.size) {
            if (contentId == contentEntity[i].contentId) {
                content = contentEntity[i]
            }
        }
        return content
    }

    fun setContentId(contentId: String) {
        this.contentId = contentId
    }
}