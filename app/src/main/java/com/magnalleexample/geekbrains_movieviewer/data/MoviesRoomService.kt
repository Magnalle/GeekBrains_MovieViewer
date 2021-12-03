package com.magnalleexample.geekbrains_movieviewer.data

import com.magnalleexample.geekbrains_movieviewer.data.externalApi.MoviesRoomDao
import com.magnalleexample.geekbrains_movieviewer.data.helperDataClasses.Room.MovieDataRoom
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Language
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo

class MoviesRoomService(private val moviesRoomDao: MoviesRoomDao) : Repo {
    override fun getWatchList(): List<MovieData> {
        return moviesRoomDao.getWatchList().map {
            MovieData(it.id,
                it.name,
                it.rating,
                it.imageURL,
                it.releaseDate,
                it.genres,
                it.overview,
                it.isInWatchList,
                it.isFavorite
        )
        }
    }

    override fun getFavoritesList(): List<MovieData> {
        return moviesRoomDao.getFavoritesList().map {
            MovieData(it.id,
                it.name,
                it.rating,
                it.imageURL,
                it.releaseDate,
                it.genres,
                it.overview,
                it.isInWatchList,
                it.isFavorite
            )
        }
    }

    override fun getLanguagesList(): List<Language> {
        return moviesRoomDao.getLanguages()
    }

    override fun getGenresList(): List<Genre> {
        return moviesRoomDao.getGenres()
    }

    override fun getTopList(
        repo: Repo,
        enableAdult: Boolean,
        languages: List<Language>?
    ): List<MovieData> {
        return listOf()
    }

    override fun synchMovieData(movieData: MovieData) {
        if(moviesRoomDao.getMovieDataByID(movieData.id).isEmpty()){
            moviesRoomDao.addMovieData(MovieDataRoom(movieData.id,
                movieData.name,
                movieData.rating,
                movieData.imageURL,
                movieData.releaseDate,
                movieData.genres,
                System.currentTimeMillis(),
                "",
                false,
                false,
                movieData.overview,
            ))
        }
    }

    override fun setMovieInWatchList(movieData: MovieData, movieInWatchList: Boolean) {
        moviesRoomDao.setMovieInWatchList(movieData.id, movieInWatchList)
    }

    override fun setMovieInFavorites(movieData: MovieData, movieInFavorites: Boolean) {
        moviesRoomDao.setMovieInFavorites(movieData.id, movieInFavorites)
    }

}