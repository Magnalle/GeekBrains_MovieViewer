package com.magnalleexample.geekbrains_movieviewer.data.externalApi

import com.magnalleexample.geekbrains_movieviewer.data.helperDataClasses.Retrofit.GenresFromApi
import com.magnalleexample.geekbrains_movieviewer.data.helperDataClasses.Retrofit.TopFromApi
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Language
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

val API_KEY = "72d61a641a885a2d08c44fe3958ff576"
interface RetrofitMoviesApi {
    @GET("3/configuration/languages")
    fun getLanguages(@Query("api_key") apiKey : String): Call<List<Language>>
    @GET("3/genre/movie/list")
    fun getGenres(@Query("api_key") apiKey : String) : Call<GenresFromApi>
    @GET("3/discover/movie")
    fun getTop(
        @Query("api_key") apiKey : String,
        @Query("sort_by") sortBy : String
        ) : Call<TopFromApi>
    @GET("3/discover/movie")
    fun getTop(
        @Query("api_key") apiKey : String,
        @Query("sort_by") sortBy : String,
        @Query("include_adult") includeAdult : Boolean,
        @Query("language") language : String
    ) : Call<TopFromApi>


}