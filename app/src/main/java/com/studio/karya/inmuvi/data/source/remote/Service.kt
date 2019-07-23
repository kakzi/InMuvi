package com.studio.karya.inmuvi.data.source.remote

import com.studio.karya.inmuvi.data.source.remote.response.MovieResponse
import com.studio.karya.inmuvi.data.source.remote.response.TvResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("/3/discover/movie")
    fun getListMovie(
        @Query("api_key") api_key: String
    ): Observable<MovieResponse>

    @GET("/3/discover/tv")
    fun getListTv(
        @Query("api_key") api_key: String
    ): Observable<TvResponse>
}