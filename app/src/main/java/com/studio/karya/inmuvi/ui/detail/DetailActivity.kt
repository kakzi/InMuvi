package com.studio.karya.inmuvi.ui.detail

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.studio.karya.inmuvi.R
import com.studio.karya.inmuvi.data.ContentEntity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val CONTENT_IDS = "ContentId"
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
        if (bundle != null) {
            val contentTYPE = bundle.getString(CONTENT_TYPE)
            val contentId = bundle.getString(CONTENT_IDS)
            if (contentId != null && contentTYPE != null) {
                viewModel.setContentId(contentId)
                populateContent(viewModel.getContent(contentTYPE))
            }
        }
        btnAction()
    }

    private fun populateContent(contentEntity: ContentEntity) {
        titleContent.text = contentEntity.titleContent
        dateContent.text = contentEntity.dateShow
        userScore.text = String.format("User Score: %s", contentEntity.userScore)
        overviewContent.text = contentEntity.overviewContent

        Glide.with(this).load(contentEntity.imgPoster).into(posterDetail)
    }

    private fun btnAction() {
        btnShare.setOnClickListener {
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
        }
    }
}
