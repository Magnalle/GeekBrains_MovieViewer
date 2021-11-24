package com.magnalleexample.geekbrains_movieviewer.data

import com.magnalleexample.geekbrains_movieviewer.data.Retrofit.RetrofitMoviesApi
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Language
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_KEY = "72d61a641a885a2d08c44fe3958ff576"
const val API_URL_STRING = "https://api.themoviedb.org/"
const val API_TOP_SORT = "popularity.desc"

class RetrofitMoviesService : Repo {
    private val retrofit = Retrofit.Builder()
        .baseUrl(API_URL_STRING)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var api: RetrofitMoviesApi = retrofit.create(RetrofitMoviesApi::class.java)
    override fun getWatchList(): List<MovieData> {
        return listOf<MovieData>()
    }

    override fun getFavoritesList(): List<MovieData> {
        return listOf<MovieData>()
    }

    override fun getLanguagesList(): List<Language> {
        return api.getLanguages(API_KEY).execute().body() ?: emptyList()
    }

    override fun getGenresList(): List<Genre> {
        return api.getGenres(API_KEY).execute().body()?.genres ?: emptyList()
    }

    override fun getTopList(repo: Repo): List<MovieData> {
        return api.getTop(API_KEY, API_TOP_SORT).execute().body()?.results?.map { movieDataFromApi ->
            MovieData(
                movieDataFromApi.id,
                movieDataFromApi.original_title,
                movieDataFromApi.vote_average,
                movieDataFromApi.poster_path,
                movieDataFromApi.release_date,
                movieDataFromApi.genre_ids.mapNotNull {
                        genreId -> repo.getGenresList().find { it.id == genreId }
                })
        }?.toList() ?: listOf()
    }
}