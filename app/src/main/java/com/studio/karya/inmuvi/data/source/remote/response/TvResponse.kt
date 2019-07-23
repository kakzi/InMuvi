package com.studio.karya.inmuvi.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TvResponse(
    @SerializedName("results")
    val tvList: MutableList<Tv>
)

@Parcelize
data class Tv(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val titleTv: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("first_air_date")
    val releaseDateTv: String?,

    @SerializedName("vote_average")
    val voteAverage: String?,

    @SerializedName("popularity")
    val popularity: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?
) : Parcelable