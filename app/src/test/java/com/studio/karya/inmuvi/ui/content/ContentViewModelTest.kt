package com.studio.karya.inmuvi.ui.content

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class ContentViewModelTest {

    private lateinit var viewModel: ContentViewModel

    @Before
    fun setUp() {
        viewModel = ContentViewModel()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getMovie() {
        assertEquals(10, viewModel.getMovie().size)
    }

    @Test
    fun getTv() {
        assertEquals(10, viewModel.getTv().size)
    }
}