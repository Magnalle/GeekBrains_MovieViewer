package com.magnalleexample.geekbrains_movieviewer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magnalleexample.geekbrains_movieviewer.data.MoviesApiService
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() , HomeInterface.ViewModel{
    lateinit var repo : Repo
    override var genresList : MutableLiveData<List<Genre>> = MutableLiveData()
    override var watchList : MutableLiveData<List<MovieData>> = MutableLiveData()
    override var favorites : MutableLiveData<List<MovieData>> = MutableLiveData()

    fun loadData(){
        viewModelScope.launch(Dispatchers.IO) {
            genresList.value = repo.getGenresList()
            watchList.value = repo.getWatchList()
            favorites.value = repo.getFavoritesList()
        }
    }

    override fun getGenresFormatted(): List<String> {
        return listOf("All genres").plus(genresList.value?.map { it.name }?: listOf())
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
