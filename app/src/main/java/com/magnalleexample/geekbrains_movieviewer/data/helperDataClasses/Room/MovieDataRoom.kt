package com.magnalleexample.geekbrains_movieviewer.data.helperDataClasses.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import java.util.*

const val MOVIE_DATA_TABLE_NAME = "movies_data"
const val MOVIE_DATA_COLUMN_ID = "id"
const val MOVIE_DATA_COLUMN_NAME = "name"
const val MOVIE_DATA_COLUMN_RATING = "rating"
const val MOVIE_DATA_COLUMN_IMAGE_URL = "image_URL"
const val MOVIE_DATA_COLUMN_RELEASE_DATE = "release_date"
const val MOVIE_DATA_COLUMN_GENRES_LIST = "genres_json"
const val MOVIE_DATA_COLUMN_LAST_UPDATE = "last_update"
const val MOVIE_DATA_COLUMN_NOTE = "note"
const val MOVIE_DATA_COLUMN_IS_FAVORITE = "is_favorite"
const val MOVIE_DATA_COLUMN_IS_IN_WATCHLIST = "is_in_watchlist"
const val MOVIE_DATA_COLUMN_OVERVIEW = "overview"

@Entity(tableName = MOVIE_DATA_TABLE_NAME)
data class MovieDataRoom(
    @PrimaryKey
    @ColumnInfo(name = MOVIE_DATA_COLUMN_ID)
    val id: Long,
    @ColumnInfo(name = MOVIE_DATA_COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = MOVIE_DATA_COLUMN_RATING)
    val rating: Double,
    @ColumnInfo(name = MOVIE_DATA_COLUMN_IMAGE_URL)
    val imageURL:String,
    @ColumnInfo(name = MOVIE_DATA_COLUMN_RELEASE_DATE)
    @TypeConverters(DateConverter::class)
    val releaseDate: Date,
    @ColumnInfo(name = MOVIE_DATA_COLUMN_GENRES_LIST)
    @TypeConverters(GenresConverter::class)
    val genres: List<Genre>,
    @ColumnInfo(name = MOVIE_DATA_COLUMN_LAST_UPDATE)
    val lastUpdate: Long,
    @ColumnInfo(name = MOVIE_DATA_COLUMN_NOTE)
    val note: String,
    @ColumnInfo(name = MOVIE_DATA_COLUMN_IS_FAVORITE)
    val isFavorite: Boolean,
    @ColumnInfo(name = MOVIE_DATA_COLUMN_IS_IN_WATCHLIST)
    val isInWatchList: Boolean,
    @ColumnInfo(name = MOVIE_DATA_COLUMN_OVERVIEW)
    val overview: String
)