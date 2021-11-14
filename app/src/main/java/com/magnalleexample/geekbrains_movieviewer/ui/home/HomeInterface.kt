package com.magnalleexample.geekbrains_movieviewer.ui.home

import androidx.lifecycle.MutableLiveData
import com.magnalleexample.geekbrains_movieviewer.data.net.MoviesApiService
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.ui.movieList.MovieListAdapter


class HomeInterface {
    interface View{
        fun navigateToMovieData(movieData: MovieData)
        var watchListAdapter : MovieListAdapter?
        var favoritesListAdapter : MovieListAdapter?
        fun updateWatchList()
        fun updateFavoritesList()
    }
    interface Presenter{
        var view: View?
        val genresList : List<Genre>
        val watchList : List<MovieData>
        val favorites : List<MovieData>
        fun attach(view : View)
        fun detach()
        fun getGenresListFormatted() : List<String>

    }
}