package com.studio.karya.inmuvi.ui.detail

import com.studio.karya.inmuvi.R
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private lateinit var movieContentEntity: ContentEntity
    private lateinit var tvContentEntity: ContentEntity

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        movieContentEntity = ContentEntity(
            "1",
            "A Star Is Born",
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            "October 3, 2018",
            "75",
            R.drawable.poster_a_start_is_born
        )

        tvContentEntity = ContentEntity(
            "1t",
            "Arrow",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "October 10, 2012",
            "58",
            R.drawable.poster_arrow
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getContentMovie() {
        viewModel.setContentId(movieContentEntity.contentId)
        assertEquals(movieContentEntity.titleContent, viewModel.getContent("movie").titleContent)
        assertEquals(movieContentEntity.dateShow, viewModel.getContent("movie").dateShow)
        assertEquals(movieContentEntity.userScore, viewModel.getContent("movie").userScore)
        assertEquals(movieContentEntity.imgPoster, viewModel.getContent("movie").imgPoster)
        assertEquals(movieContentEntity.overviewContent, viewModel.getContent("movie").overviewContent)
    }

    @Test
    fun getContentTv() {
        viewModel.setContentId(tvContentEntity.contentId)
        assertEquals(tvContentEntity.titleContent, viewModel.getContent("tv").titleContent)
        assertEquals(tvContentEntity.dateShow, viewModel.getContent("tv").dateShow)
        assertEquals(tvContentEntity.userScore, viewModel.getContent("tv").userScore)
        assertEquals(tvContentEntity.imgPoster, viewModel.getContent("tv").imgPoster)
        assertEquals(tvContentEntity.overviewContent, viewModel.getContent("tv").overviewContent)
    }
}