package com.studio.karya.inmuvi.ui.detail

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.studio.karya.inmuvi.R
import com.studio.karya.inmuvi.ui.detail.DetailActivity.Companion.CONTENT_IDS
import com.studio.karya.inmuvi.ui.detail.DetailActivity.Companion.CONTENT_TYPE
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailActivityTest {

    private val content = Data().generateMovie()

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<DetailActivity> =
        object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContent = InstrumentationRegistry.getInstrumentation().targetContext
                val intent = Intent(targetContent, DetailActivity::class.java)
                intent.putExtra(CONTENT_IDS, content[0].contentId)
                intent.putExtra(CONTENT_TYPE, "movie")
                return intent
            }
        }

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun loadContentOverview() {
        onView(withId(R.id.overviewContent))
            .check(matches(withText(content[0].overviewContent)))
    }
}