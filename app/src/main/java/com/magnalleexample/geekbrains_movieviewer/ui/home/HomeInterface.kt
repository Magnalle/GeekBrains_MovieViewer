package com.magnalleexample.geekbrains_movieviewer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData

class HomeInterface {
    interface View{
        fun navigateToMovieData(movieData: MovieData?)
    }
    interface ViewModel{
        val genresList : MutableLiveData<List<Genre>>?
        val watchList : MutableLiveData<List<MovieData>>?
        val favorites : MutableLiveData<List<MovieData>>?
        fun getGenresFormatted() : List<String>
    }
}