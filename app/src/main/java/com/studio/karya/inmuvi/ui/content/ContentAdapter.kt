package com.studio.karya.inmuvi.ui.content

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.studio.karya.inmuvi.BuildConfig.BASE_URL_IMAGE
import com.studio.karya.inmuvi.R
import com.studio.karya.inmuvi.data.source.remote.response.Content
import com.studio.karya.inmuvi.ui.detail.DetailActivity
import com.studio.karya.inmuvi.utils.toPx
import kotlinx.android.synthetic.main.items_content.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class ContentAdapter(private val activity: Activity, private val callback: ContentCallback) :
    RecyclerView.Adapter<Holder>() {

    private val listContent: MutableList<Content> = mutableListOf()

    private fun getListContent(): MutableList<Content> {
        return listContent
    }

    fun setListContent(listMovie: MutableList<Content>) {
        this.listContent.clear()
        this.listContent.addAll(listMovie)
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

        callback.setTitleContent(getListContent())
        holder.bindItem(content)
        holder.itemView.layoutParams.width = width - 90.toPx(holder.itemView.context)
        holder.itemView.setOnClickListener {
            activity.startActivity<DetailActivity>()
        }
    }
}

class Holder(private val view: View): RecyclerView.ViewHolder(view){
    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(content: Content){
        Glide
            .with(view.context)
            .load(BASE_URL_IMAGE+ content.posterPath)
            .into(imgPoster)
    }
}