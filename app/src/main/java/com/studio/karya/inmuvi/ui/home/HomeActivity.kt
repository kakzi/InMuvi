package com.studio.karya.inmuvi.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.studio.karya.inmuvi.R
import com.studio.karya.inmuvi.ui.content.movie.MovieFragment
import com.studio.karya.inmuvi.ui.content.tv.TvFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    companion object {
        private const val SELECTED_MENU = "selected_menu"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()

        if (savedInstanceState != null) {
            if (savedInstanceState.getInt(SELECTED_MENU) == R.id.actionMovie) {
                headTitle.text = getString(R.string.head_title_movie)
            } else {
                headTitle.text = getString(R.string.head_title_tv)
            }
        } else {
            bottomNav.selectedItemId = R.id.actionMovie
        }
    }

    private fun init() {
        bottomNav.setOnNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.actionMovie -> {
                    val movie = MovieFragment().newInstance()
                    fragment = movie
                    headTitle.text = getString(R.string.head_title_movie)
                }
                R.id.actionTv -> {
                    val tv = TvFragment().newInstance()
                    fragment = tv
                    headTitle.text = getString(R.string.head_title_tv)
                }
            }

            if (fragment != null) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            }
            true
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(SELECTED_MENU, bottomNav.selectedItemId)
    }
}
