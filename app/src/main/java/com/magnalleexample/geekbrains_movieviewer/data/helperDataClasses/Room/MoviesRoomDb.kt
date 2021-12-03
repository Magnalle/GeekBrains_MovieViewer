package com.magnalleexample.geekbrains_movieviewer.data.helperDataClasses.Room

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.magnalleexample.geekbrains_movieviewer.data.externalApi.MoviesRoomDao
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Language

@Database(
    entities = [MovieDataRoom::class, Language::class, Genre::class],
    version = 1
)
@TypeConverters(GenresConverter::class, DateConverter::class)
abstract class MoviesRoomDb : RoomDatabase() {
    abstract fun moviesDao(): MoviesRoomDao
}