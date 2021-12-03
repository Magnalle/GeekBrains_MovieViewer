package com.magnalleexample.geekbrains_movieviewer.data

import com.magnalleexample.geekbrains_movieviewer.App
import com.magnalleexample.geekbrains_movieviewer.data.externalApi.MoviesRoomDao
import com.magnalleexample.geekbrains_movieviewer.data.helperDataClasses.Room.MoviesRoomDb
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Language
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import java.util.*

class WorkingRepo(val moviesRoomDao : MoviesRoomDao) : Repo {
    //val moviesApiService : MoviesApiService by lazy {MoviesApiService()}
    val moviesApiService : RetrofitMoviesService by lazy {RetrofitMoviesService()}
    val moviesRoomService : MoviesRoomService by lazy {MoviesRoomService(moviesRoomDao)}

    private var _languagesList : List<Language>? = null
    private var _genresList : List<Genre>? = null
    override fun getWatchList(): List<MovieData> {
        return moviesRoomService.getWatchList()
    }

    override fun getFavoritesList(): List<MovieData> {
        return moviesRoomService.getFavoritesList()
    }

    override fun getLanguagesList(): List<Language> {
        if (_languagesList == null){
            _languagesList = moviesRoomService.getLanguagesList()
            _languagesList?.let{listFromRoom->
                if(listFromRoom.isEmpty()) {
                    _languagesList = moviesApiService.getLanguagesList()
                    _languagesList?.let{list->
                        list.forEach { moviesRoomDao.addLanguage(it) }
                    }
                }
            }
        }
        return _languagesList?: listOf()
    }

    override fun getGenresList(): List<Genre> {
        if (_genresList == null){
            _genresList = moviesRoomService.getGenresList()
            _genresList?.let{listFromRoom->
                if(listFromRoom.isEmpty()) {
                    _genresList = moviesApiService.getGenresList()
                    _genresList?.let{list->
                        list.forEach { moviesRoomDao.addGenre(it) }
                    }
                }
            }
        }
        return _genresList?: listOf()
    }

    override fun getTopList(
        repo: Repo,
        enableAdult: Boolean,
        languages: List<Language>?
    ): List<MovieData>{
        val topList = moviesApiService.getTopList(this, enableAdult, languages)
        val watchlist = getWatchList()
        val favoritesList = getFavoritesList()
        topList.forEach {topListMovieData ->
            watchlist.find { it.id == topListMovieData.id }?.let { topListMovieData.inWatchList = true }
            favoritesList.find { it.id == topListMovieData.id }?.let { topListMovieData.inFavorites = true }
        }
        return topList
    }

    override fun synchMovieData(movieData: MovieData) {
        moviesRoomService.synchMovieData(movieData)
    }

    override fun setMovieInWatchList(movieData: MovieData, movieInWatchList: Boolean) {
        moviesRoomService.setMovieInWatchList(movieData, movieInWatchList)
    }

    override fun setMovieInFavorites(movieData: MovieData, movieInFavorites: Boolean) {
        moviesRoomService.setMovieInFavorites(movieData, movieInFavorites)
    }


}