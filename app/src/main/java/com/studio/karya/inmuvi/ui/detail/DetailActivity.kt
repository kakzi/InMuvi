package com.studio.karya.inmuvi.ui.detail

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.studio.karya.inmuvi.BuildConfig.BASE_URL_IMAGE
import com.studio.karya.inmuvi.R
import com.studio.karya.inmuvi.data.source.remote.response.Content
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val CONTENT = "Content"
        const val CONTENT_TYPE = "ContentType"
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT)
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        val bundle = intent.extras

        bundle?.apply {
            val movie = getParcelable<Content>(CONTENT)
            populateContent(movie)
        }
        btnAction()
    }

    private fun populateContent(movie: Content) {
        titleContent.text = movie.title
        dateContent.text = movie.releaseDate
        userScore.text = String.format("User Score: %s", movie.voteAverage)
        overviewContent.text = movie.overview

        Glide.with(this).load(BASE_URL_IMAGE+movie.posterPath).into(posterDetail)
        Glide.with(this).load(BASE_URL_IMAGE+movie.backdropPath).into(backdrop_image)

    }

    private fun btnAction() {
        /*btnShare.setOnClickListener {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Bagikan info ini!")
                .setText(
                    String.format(
                        "%1s, %2s",
                        viewModel.getContent(CONTENT_TYPE).titleContent,
                        viewModel.getContent(CONTENT_TYPE).overviewContent
                    )
                )
                .startChooser()
        }*/
    }
}
