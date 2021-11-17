package com.magnalleexample.geekbrains_movieviewer.ui.top

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo

class TopViewModel : ViewModel() {
    lateinit var repo : Repo
    val topList : MutableLiveData<List<MovieData>> by lazy { MutableLiveData(repo.getTopList(repo)) }
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