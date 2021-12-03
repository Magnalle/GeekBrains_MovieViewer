package com.magnalleexample.geekbrains_movieviewer.data

import com.magnalleexample.geekbrains_movieviewer.data.externalApi.RetrofitMoviesApi
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Language
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat

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
        return api.getLanguages(Repo.API_KEY).execute().body()?.sortedBy { it.english_name } ?: emptyList()
    }

    override fun getGenresList(): List<Genre> {
        return api.getGenres(Repo.API_KEY).execute().body()?.genres ?: emptyList()
    }

    override fun getTopList(
        repo: Repo,
        enableAdult: Boolean,
        languages: List<Language>?
    ): List<MovieData> {
        return api.getTop(Repo.API_KEY, Repo.API_TOP_SORT, enableAdult, languages?.joinToString()?:"").execute().body()?.results?.map { movieDataFromApi ->
            MovieData(
                movieDataFromApi.id,
                movieDataFromApi.original_title,
                movieDataFromApi.vote_average,
                movieDataFromApi.poster_path,
                SimpleDateFormat("yyyy-MM-dd").parse(movieDataFromApi.release_date),
                movieDataFromApi.genre_ids.mapNotNull {
                        genreId -> repo.getGenresList().find { it.id == genreId }
                },
                movieDataFromApi.overview,
            )
        }?.toList() ?: listOf()
    }

    override fun synchMovieData(movieData: MovieData) {
    }

    override fun setMovieInWatchList(movieData: MovieData, movieInWatchList: Boolean) {
    }

    override fun setMovieInFavorites(movieData: MovieData, movieInFavorites: Boolean) {
    }

}