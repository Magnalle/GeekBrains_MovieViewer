package com.magnalleexample.geekbrains_movieviewer.data

import com.google.gson.Gson
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Language
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieDataFromApi
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MoviesApiService : Repo {
    val apiKey = "72d61a641a885a2d08c44fe3958ff576"
    val apiURLString = "https://api.themoviedb.org/3"
    val gson : Gson by lazy {Gson()}
    override fun getWatchList(): List<MovieData> {
        return listOf<MovieData>()
    }

    override fun getFavoritesList(): List<MovieData> {
        return listOf<MovieData>()
    }
    @Synchronized
    override fun getLanguagesList(): List<Language> {
        var connection : HttpURLConnection? = null
        var result : List<Language>
        try{
            connection = URL("$apiURLString/configuration/languages?api_key=$apiKey").openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 5_000

            val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))

            result = gson.fromJson(bufferedReader, Array<Language>::class.java).asList()
        }
        finally {
            connection?.disconnect()
        }
        return result
    }

    override fun getGenresList(): List<Genre> {
        var connection : HttpURLConnection? = null
        var result : List<Genre>
        try{
            connection = URL("$apiURLString/genre/movie/list?api_key=$apiKey").openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 5_000

            val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))

            result = gson.fromJson(bufferedReader,
                object{
                    var genres : Array<Genre>? = null
                }::class.java).genres?.asList()?: listOf()
        }
        finally {
            connection?.disconnect()
        }
        return result
    }

    override fun getTopList(repo: Repo): List<MovieData> {
        var connection : HttpURLConnection? = null
        var result : List<MovieData>
        try{
            connection = URL("$apiURLString/discover/movie?sort_by=popularity.desc&api_key=$apiKey").openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 5_000

            val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))

            result = gson.fromJson(bufferedReader,
                object{
                    var page: Long = 1
                    var result : Array<MovieDataFromApi>? = null
                }::class.java).result?.map { movieDataFromApi ->
                    MovieData(
                        movieDataFromApi.id,
                        movieDataFromApi.original_title,
                        movieDataFromApi.vote_average,
                        movieDataFromApi.poster_path,
                        movieDataFromApi.release_date,
                        movieDataFromApi.genre_ids.mapNotNull {
                                genreId -> repo.getGenresList().find { it.id == genreId.id }
                        })
                }?.toList() ?: listOf()
        }
        finally {
            connection?.disconnect()
        }
        return result
    }

}