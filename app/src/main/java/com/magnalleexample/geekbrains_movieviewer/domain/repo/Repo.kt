package com.magnalleexample.geekbrains_movieviewer.domain.repo

import com.magnalleexample.geekbrains_movieviewer.data.net.MoviesApiService
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Language
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData

object Repo {
    private val _watchList : MutableList<MovieData> = MoviesApiService.getWatchList()
    val watchList : List<MovieData>
        get() = _watchList.toList()

    private val _favorites : MutableList<MovieData> = MoviesApiService.getWatchList()
    val favorites : List<MovieData>
        get() = _favorites.toList()

    val languages : List<Language>
        get() = MoviesApiService.getLanguages()

    val genres : List<Genre>
        get() = MoviesApiService.getGenres()

    fun addToWatchlist(movie : MovieData){
        _watchList.add(movie)
        MoviesApiService.addToFavorites(movie)
    }
}