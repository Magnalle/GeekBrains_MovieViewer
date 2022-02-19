package com.magnalleexample.geekbrains_movieviewer.ui.top

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import com.magnalleexample.geekbrains_movieviewer.ui.home.HomeFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopViewModel : ViewModel() {
    lateinit var repo : Repo
    val genresList : MutableLiveData<List<Genre>> = MutableLiveData()
    val topList : MutableLiveData<List<MovieData>> = MutableLiveData()
    fun loadData(){
        viewModelScope.launch(Dispatchers.IO) {
            val temp = repo.getTopList(repo)
            viewModelScope.launch(Dispatchers.Main) {
                topList.postValue(temp)
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            val temp = repo.getGenresList()
            viewModelScope.launch(Dispatchers.Main) {
                genresList.value = temp
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

    fun getGenresFormatted(): List<String> {
        return listOf("All genres").plus(genresList.value?.map { it.name }?: listOf())
    }
}