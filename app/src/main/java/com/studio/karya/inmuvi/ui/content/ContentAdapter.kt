package com.studio.karya.inmuvi.ui.content

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.studio.karya.inmuvi.R
import com.studio.karya.inmuvi.data.ContentEntity
import com.studio.karya.inmuvi.ui.detail.DetailActivity
import com.studio.karya.inmuvi.ui.detail.DetailActivity.Companion.CONTENT_IDS
import com.studio.karya.inmuvi.ui.detail.DetailActivity.Companion.CONTENT_TYPE
import com.studio.karya.inmuvi.utils.toPx
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class ContentAdapter(private val activity: Activity) : RecyclerView.Adapter<Holder>() {

    private val mContent: MutableList<ContentEntity> = mutableListOf()
    private lateinit var type: String

    private fun getListContent(): MutableList<ContentEntity> {
        return mContent
    }

    fun setListContent(contentEntity: MutableList<ContentEntity>, type: String) {
        mContent.clear()
        mContent.addAll(contentEntity)
        this.type = type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.items_content, parent, false)
        )
    }

    override fun getItemCount(): Int = getListContent().size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val content = getListContent()[position]
        val width = holder.itemView.context.resources.displayMetrics.widthPixels

        holder.bindItem(content)
        holder.itemView.layoutParams.width = width - 90.toPx(holder.itemView.context)
        holder.itemView.setOnClickListener {
            activity.startActivity<DetailActivity>(
                CONTENT_IDS to content.contentId,
                CONTENT_TYPE to type
            )
        }
    }
}

class Holder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(contentEntity: ContentEntity) {
        Glide
            .with(view.context)
            .load(contentEntity.imgPoster)
            .into(imgPoster)
    }
}