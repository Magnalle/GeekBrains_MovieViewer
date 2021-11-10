package com.magnalleexample.geekbrains_movieviewer.data.net

import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Language
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData

object MoviesApiService {
    fun getGenres(): List<Genre>{
        return listOf(Genre(1, "Action"), Genre(1, "Fantasy"))
        TODO("")
    }

    fun getLanguages() : List<Language>{
        return listOf(Language(1, "En"))
        TODO("")
    }

    fun getWatchList() : MutableList<MovieData>{
        return mutableListOf(MovieData(1, "Some movie 1", 5.0, "", 2020), MovieData(2, "Some movie 2", 5.0, "", 2020))
        TODO("")
    }

    fun getFavorites() : MutableList<MovieData>{
        return mutableListOf(MovieData(1, "Favorite movie", 5.0, "", 2020), MovieData(2, "Favorite movie 2", 5.0, "", 2020))
        TODO("")
    }

    fun addToWatchList(movie: MovieData){
        return
        TODO("")
    }
    fun addToFavorites(movie: MovieData){
        return
        TODO("")
    }
}