package com.studio.karya.inmuvi.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MovieResponse(
    @SerializedName("results")
    val movieList: MutableList<Movie>
)

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    /*@SerializedName("first_air_date") //tv
    val releaseDateTv: String?,*/

    @SerializedName("vote_average")
    val voteAverage: String?,

    @SerializedName("popularity")
    val popularity: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?
) : Parcelable
