package com.studio.karya.inmuvi.ui.content.tv

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.studio.karya.inmuvi.BuildConfig.BASE_URL_IMAGE
import com.studio.karya.inmuvi.R
import com.studio.karya.inmuvi.data.source.remote.response.Tv
import com.studio.karya.inmuvi.ui.detail.DetailActivity
import com.studio.karya.inmuvi.ui.detail.DetailActivity.Companion.CONTENT
import com.studio.karya.inmuvi.utils.toPx
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class TvAdapter(private val activity: Activity, private val callback: TvCallback) :
    RecyclerView.Adapter<Holder>() {

    private val listTv: MutableList<Tv> = mutableListOf()

    fun setListTv(listTv: MutableList<Tv>) {
        this.listTv.clear()
        this.listTv.addAll(listTv)
    }

    private fun getListTv(): MutableList<Tv> {
        return listTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.items_content, parent, false)
        )
    }

    override fun getItemCount(): Int = getListTv().size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val content = getListTv()[position]
        val width = holder.itemView.context.resources.displayMetrics.widthPixels

        //send to interface
        callback.setTitleTv(getListTv())

        val tv = Tv(
            content.id,
            content.titleTv,
            content.overview,
            content.releaseDateTv,
            content.voteAverage,
            content.popularity,
            content.posterPath,
            content.backdropPath
        )

        holder.bindItem(content)
        holder.itemView.layoutParams.width = width - 90.toPx(holder.itemView.context)
        holder.itemView.setOnClickListener {
            activity.startActivity<DetailActivity>(
                CONTENT to tv
            )
        }
    }
}

class Holder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(tvEntity: Tv) {
        Glide
            .with(view.context)
            .load(BASE_URL_IMAGE + tvEntity.posterPath)
            .into(imgPoster)
    }
}