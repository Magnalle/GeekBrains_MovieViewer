package com.magnalleexample.geekbrains_movieviewer.data

import com.magnalleexample.geekbrains_movieviewer.data.Retrofit.RetrofitMoviesApi
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Language
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitMoviesService : Repo {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Repo.API_URL_STRING)
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
        return api.getLanguages(Repo.API_KEY).execute().body() ?: emptyList()
    }

    override fun getGenresList(): List<Genre> {
        return api.getGenres(Repo.API_KEY).execute().body()?.genres ?: emptyList()
    }

    override fun getTopList(repo: Repo): List<MovieData> {
        return api.getTop(Repo.API_KEY, Repo.API_TOP_SORT).execute().body()?.results?.map { movieDataFromApi ->
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