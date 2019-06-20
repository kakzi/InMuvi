package com.studio.karya.inmuvi.ui.home

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.studio.karya.inmuvi.R
import com.studio.karya.inmuvi.ui.movie.MovieFragment
import com.studio.karya.inmuvi.ui.tv.TvFragment
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
            savedInstanceState.getInt(SELECTED_MENU)
        } else {
            bottomNav.selectedItemId = R.id.actionMovie
        }
    }

    private fun init() {
        bottomNav.setOnNavigationItemSelectedListener { item ->

            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.actionMovie -> {
                    fragment = MovieFragment().newInstance()
                    headTitle.text = getString(R.string.head_title_movie)
                }
                R.id.actionTv -> {
                    fragment = TvFragment().newInstance()
                    headTitle.text = getString(R.string.head_title_tv)
                }
            }

            if (fragment != null) {
                supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container, fragment)
                    .commit()
            }
            true
        }
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putInt(SELECTED_MENU, bottomNav.selectedItemId)
    }
}
