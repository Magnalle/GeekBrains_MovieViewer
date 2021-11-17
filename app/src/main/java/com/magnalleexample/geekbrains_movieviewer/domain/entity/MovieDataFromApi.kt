package com.magnalleexample.geekbrains_movieviewer.domain.entity

data class MovieDataFromApi(
    val id: Long,
    val original_language : String,
    val title : String,
    val original_title : String,
    val vote_average : Double,
    val popularity : Double,
    val overview : String,
    val release_date : String,
    val genre_ids : Array<Int>,
    val poster_path : String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieDataFromApi

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

