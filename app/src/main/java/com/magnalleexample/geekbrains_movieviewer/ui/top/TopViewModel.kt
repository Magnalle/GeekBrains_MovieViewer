package com.magnalleexample.geekbrains_movieviewer.ui.top

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopViewModel : ViewModel() {
    lateinit var repo : Repo
    val topList : MutableLiveData<List<MovieData>> = MutableLiveData()
    fun loadData(){
        viewModelScope.launch(Dispatchers.IO) {
            val temp = repo.getTopList(repo)
            viewModelScope.launch(Dispatchers.Main) {
                topList.postValue(temp)
            }
        }
    }
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