package com.magnalleexample.geekbrains_movieviewer.domain.repo

import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Language
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData

interface Repo {
    companion object{
        const val API_KEY = "72d61a641a885a2d08c44fe3958ff576"
        const val API_URL_STRING = "https://api.themoviedb.org/"
        const val API_TOP_SORT = "popularity.desc"
    }
    fun getWatchList() : List<MovieData>
    fun getFavoritesList() : List<MovieData>
    fun getLanguagesList() : List<Language>
    fun getGenresList() : List<Genre>
    fun getTopList(repo: Repo, enableAdult: Boolean = false, languages: List<Language>? = null): List<MovieData>
    fun synchMovieData(movieData: MovieData)
    fun setMovieInWatchList(movieData: MovieData, movieInWatchList : Boolean)
    fun setMovieInFavorites(movieData: MovieData, movieInFavorites : Boolean)
}