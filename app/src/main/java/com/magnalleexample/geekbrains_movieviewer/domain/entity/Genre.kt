package com.magnalleexample.geekbrains_movieviewer.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val GENRE_TABLE_NAME = "genres"
const val GENRE_COLUMN_ID = "id"
const val GENRE_COLUMN_NAME = "name"

@Entity(tableName = GENRE_TABLE_NAME)
data class Genre(
    @PrimaryKey
    @ColumnInfo(name = GENRE_COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = GENRE_COLUMN_NAME)
    val name : String
)
