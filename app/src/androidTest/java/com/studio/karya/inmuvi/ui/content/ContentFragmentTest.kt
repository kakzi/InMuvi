package com.studio.karya.inmuvi.ui.content

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.studio.karya.inmuvi.R
import com.studio.karya.inmuvi.testing.SingleFragmentActivity
import com.studio.karya.inmuvi.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test

class ContentFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val contentFragment = ContentFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(contentFragment)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun loadFragment() {
        onView(withId(R.id.rv_content))
            .check(RecyclerViewItemCountAssertion(10))
    }
}