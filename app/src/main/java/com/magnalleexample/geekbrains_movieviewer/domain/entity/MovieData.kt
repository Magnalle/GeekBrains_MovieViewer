package com.magnalleexample.geekbrains_movieviewer.domain.entity

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.core.text.color
import androidx.core.text.toSpannable
import androidx.core.text.toSpanned
import java.io.Serializable
import java.lang.StringBuilder
import java.util.*

data class MovieData(
    val id: Long,
    val name: String,
    val rating: Double,
    val imageURL: String,
    val releaseDate: Date,
    val genres: List<Genre>,
    val overview : String,
    var inWatchList : Boolean = false,
    var inFavorites : Boolean = false
) : Serializable{
    fun genresToText() : String {
        val stringBuilder = StringBuilder()
        genres.forEach{genre ->
            stringBuilder
                .append(if (stringBuilder.isNotEmpty()) ", " else "")
                .append(genre.name)
        }
        return stringBuilder.toString()
    }
}
