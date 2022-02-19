package com.magnalleexample.geekbrains_movieviewer.domain.repo

import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Language
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData

interface Repo {
    fun getWatchList() : List<MovieData>
    fun getFavoritesList() : List<MovieData>
    fun getLanguagesList() : List<Language>
    fun getGenresList() : List<Genre>
    fun getTopList(repo: Repo) : List<MovieData>
}