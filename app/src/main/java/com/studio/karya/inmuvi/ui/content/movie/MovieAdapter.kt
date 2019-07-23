package com.studio.karya.inmuvi.ui.content.movie

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.studio.karya.inmuvi.BuildConfig.BASE_URL_IMAGE
import com.studio.karya.inmuvi.R
import com.studio.karya.inmuvi.data.source.remote.response.Movie
import com.studio.karya.inmuvi.ui.detail.DetailActivity
import com.studio.karya.inmuvi.ui.detail.DetailActivity.Companion.CONTENT
import com.studio.karya.inmuvi.utils.toPx
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class MovieAdapter(private val activity: Activity, private val callback: MovieCallback) :
    RecyclerView.Adapter<Holder>() {

    private val listMovie: MutableList<Movie> = mutableListOf()

    private fun getListMovie(): MutableList<Movie> {
        return listMovie
    }

    fun setListMovie(listMovie: MutableList<Movie>) {
        this.listMovie.clear()
        this.listMovie.addAll(listMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.items_content, parent, false)
        )
    }

    override fun getItemCount(): Int = getListMovie().size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val content = getListMovie()[position]
        val width = holder.itemView.context.resources.displayMetrics.widthPixels

        callback.setTitleMovie(getListMovie())

        val movie = Movie(
            content.id,
            content.title,
            content.overview,
            content.releaseDate,
            content.voteAverage,
            content.popularity,
            content.posterPath,
            content.backdropPath
        )

        holder.bindItem(content)
        holder.itemView.layoutParams.width = width - 90.toPx(holder.itemView.context)
        holder.itemView.setOnClickListener {
            activity.startActivity<DetailActivity>(
                CONTENT to movie
            )
        }
    }
}

class Holder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(movieEntity: Movie) {
        Glide
            .with(view.context)
            .load(BASE_URL_IMAGE + movieEntity.posterPath)
            .into(imgPoster)
    }
}