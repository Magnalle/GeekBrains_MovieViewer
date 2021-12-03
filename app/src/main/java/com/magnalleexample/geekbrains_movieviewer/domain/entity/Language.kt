package com.magnalleexample.geekbrains_movieviewer.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val LANGUAGE_TABLE_NAME = "languages"
const val LANGUAGE_COLUMN_ID = "iso_639_1"
const val LANGUAGE_COLUMN_NAME = "name"
const val LANGUAGE_COLUMN_ENGLISH_NAME = "english_name"

@Entity(tableName = LANGUAGE_TABLE_NAME)
data class Language(
    @PrimaryKey
    @ColumnInfo(name = LANGUAGE_COLUMN_ID)
    val iso_639_1: String,
    @ColumnInfo(name = LANGUAGE_COLUMN_ENGLISH_NAME)
    val english_name : String,
    @ColumnInfo(name = LANGUAGE_COLUMN_NAME)
    val name : String
)
