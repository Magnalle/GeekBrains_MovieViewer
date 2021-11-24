package com.magnalleexample.geekbrains_movieviewer.data

import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Language
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo

class WorkingRepo : Repo {
    //val moviesApiService : MoviesApiService by lazy {MoviesApiService()}
    val moviesApiService : RetrofitMoviesService by lazy {RetrofitMoviesService()}
    private var _languagesList : List<Language>? = null
    private var _genresList : List<Genre>? = null
    override fun getWatchList(): List<MovieData> {
        return mutableListOf(
            MovieData(1, "Some movie 1", 5.0, "", "2020",listOf()),
            MovieData(2, "Some movie 2", 5.0, "", "2020", listOf())
        )
    }

    override fun getFavoritesList(): List<MovieData> {
        return mutableListOf(
            MovieData(1, "Favorite movie", 5.0, "", "2020", listOf()),
            MovieData(2, "Favorite movie 2", 5.0, "", "2020", listOf())
        )
    }

    override fun getLanguagesList(): List<Language> {
        if (_languagesList == null){
            _languagesList = moviesApiService.getLanguagesList()
        }
        return _languagesList?: listOf()
    }

    override fun getGenresList(): List<Genre> {
        if (_genresList == null){
            _genresList = moviesApiService.getGenresList()
        }
        return _genresList?: listOf()
    }

    override fun getTopList(repo: Repo): List<MovieData> {
        return moviesApiService.getTopList(this)
    }
}