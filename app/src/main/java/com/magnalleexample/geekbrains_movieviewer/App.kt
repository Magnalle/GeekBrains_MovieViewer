package com.magnalleexample.geekbrains_movieviewer

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.magnalleexample.geekbrains_movieviewer.data.WorkingRepo
import com.magnalleexample.geekbrains_movieviewer.data.externalApi.MoviesRoomDao
import com.magnalleexample.geekbrains_movieviewer.data.helperDataClasses.Room.MoviesRoomDb
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo

private const val SHARED_PREF_NAME = "root_preferences"
private const val DB_PATH = "movies.db"

class App : Application() {
    val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            SHARED_PREF_NAME,
            MODE_PRIVATE
        )
    }
    private val moviesRoomDb: MoviesRoomDb by lazy {
        Room.databaseBuilder(
            this,
            MoviesRoomDb::class.java,
            DB_PATH)
            .build() }
    private val moviesRoomDao: MoviesRoomDao by lazy {
        moviesRoomDb.moviesDao()
        }
    val repository : Repo by lazy { WorkingRepo(moviesRoomDao) }
}

val Context.app
    get() = applicationContext as App