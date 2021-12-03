package com.magnalleexample.geekbrains_movieviewer.data.externalApi

import androidx.room.*
import com.magnalleexample.geekbrains_movieviewer.data.helperDataClasses.Room.*
import com.magnalleexample.geekbrains_movieviewer.domain.entity.*

@Dao
@TypeConverters(GenresConverter::class)
interface MoviesRoomDao {
    @Insert
    fun addGenre(genre: Genre)

    @Query("SELECT * FROM $GENRE_TABLE_NAME")
    fun getGenres(): List<Genre>

    @Query("DELETE FROM $GENRE_TABLE_NAME")
    fun clearGenres()

    @Insert
    fun addLanguage(language: Language)

    @Query("SELECT * FROM $LANGUAGE_TABLE_NAME")
    fun getLanguages(): List<Language>

    @Query("DELETE FROM $LANGUAGE_TABLE_NAME")
    fun clearLanguages()

    @Insert
    fun addMovieData(movieData: MovieDataRoom)

    @Delete
    fun deleteMovieData(movieData: MovieDataRoom)

    @Query("SELECT * FROM $MOVIE_DATA_TABLE_NAME")
    fun getMovieData(): List<MovieDataRoom>

    @Query("SELECT * FROM $MOVIE_DATA_TABLE_NAME WHERE $MOVIE_DATA_COLUMN_ID = :id")
    fun getMovieDataByID(id : Long): List<MovieDataRoom>

    @Update(entity = MovieDataRoom::class)
    fun updateMovieData(movieData: MovieDataRoom)

    @Query("DELETE FROM $MOVIE_DATA_TABLE_NAME")
    fun clearMovieData()

    @Query("SELECT * FROM $MOVIE_DATA_TABLE_NAME WHERE $MOVIE_DATA_COLUMN_IS_FAVORITE = 1")
    fun getFavoritesList(): List<MovieDataRoom>

    @Query("SELECT * FROM $MOVIE_DATA_TABLE_NAME WHERE $MOVIE_DATA_COLUMN_IS_IN_WATCHLIST = 1")
    fun getWatchList(): List<MovieDataRoom>

    @Query("SELECT * FROM $MOVIE_DATA_TABLE_NAME WHERE $MOVIE_DATA_COLUMN_IS_FAVORITE = 1 AND id = :movieDataId LIMIT 1")
    fun isFavorite(movieDataId: Long) : List<MovieDataRoom>

    @Query("SELECT * FROM $MOVIE_DATA_TABLE_NAME WHERE $MOVIE_DATA_COLUMN_IS_IN_WATCHLIST = 1 AND id = :movieDataId LIMIT 1")
    fun isInWatchList(movieDataId: Long) : List<MovieDataRoom>

    @Query("UPDATE $MOVIE_DATA_TABLE_NAME SET $MOVIE_DATA_COLUMN_IS_IN_WATCHLIST = :value WHERE id = :id")
    fun setMovieInWatchList(id : Long, value : Boolean)

    @Query("UPDATE $MOVIE_DATA_TABLE_NAME SET $MOVIE_DATA_COLUMN_IS_FAVORITE = :value WHERE id = :id")
    fun setMovieInFavorites(id : Long, value : Boolean)
}