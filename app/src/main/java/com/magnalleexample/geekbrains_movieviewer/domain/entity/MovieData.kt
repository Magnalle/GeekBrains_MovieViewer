package com.magnalleexample.geekbrains_movieviewer.domain.entity

import java.io.Serializable


data class MovieData(
    val id: Long,
    val name: String,
    val rating: Double,
    val imageURL:String,
    val year: String,
    val genres: List<Genre>
) : Serializable
