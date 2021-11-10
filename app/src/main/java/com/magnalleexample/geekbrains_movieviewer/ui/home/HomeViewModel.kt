package com.magnalleexample.geekbrains_movieviewer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magnalleexample.geekbrains_movieviewer.data.net.MoviesApiService
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo

class HomeViewModel : ViewModel() {
    val genresList : List<Genre> = MoviesApiService.getGenres()
    val watchList : MutableLiveData<List<MovieData>> = MutableLiveData(Repo.watchList)
    val favorites : MutableLiveData<List<MovieData>> = MutableLiveData(Repo.favorites)

    var _response = MutableLiveData<String>()

    private var _navigateToMovieData = MutableLiveData<MovieData?>()
    val navigateToMovieData
        get() = _navigateToMovieData

    fun onMovieClicked(movieData: MovieData){
        _navigateToMovieData.postValue(movieData)
    }

    fun doneNavigating() {
        _navigateToMovieData.value = null
    }

}
