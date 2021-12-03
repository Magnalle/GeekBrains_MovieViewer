package com.magnalleexample.geekbrains_movieviewer.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val ERROR_LOADING_FROM_REPOSITORY = "Can not load data from repository! Error: "

class HomeViewModel : ViewModel(), HomeInterface.ViewModel {
    lateinit var repo: Repo
    override val genresList: MutableLiveData<List<Genre>> = MutableLiveData()
    override val watchList: MutableLiveData<List<MovieData>> = MutableLiveData()
    override val favorites: MutableLiveData<List<MovieData>> = MutableLiveData()
    private var _navigateToMovieData = MutableLiveData<MovieData?>()
    val navigateToMovieData
        get() = _navigateToMovieData
    private val exceptionHandler = CoroutineExceptionHandler { _, error ->
        error.message?.let {
            Log.d("Repo", "$ERROR_LOADING_FROM_REPOSITORY$it")
            _errorMessage.postValue("$ERROR_LOADING_FROM_REPOSITORY$it")
        }
    }
    private var _errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    val errorMessage
        get() = _errorMessage

    fun loadData() {
        viewModelScope.launch(exceptionHandler) {
            _loadData()
        }
    }

    private suspend fun _loadData() = withContext(Dispatchers.IO) {
        genresList.postValue(repo.getGenresList())
        watchList.postValue(repo.getWatchList())
        favorites.postValue(repo.getFavoritesList())
    }
    override fun getGenresFormatted(): List<String> {
        return listOf("All genres").plus(genresList.value?.map { it.name } ?: listOf())
    }

    fun onMovieClicked(movieData: MovieData) {
        _navigateToMovieData.postValue(movieData)
    }

    fun doneNavigating() {
        _navigateToMovieData.value = null
    }

    fun disableError() {
        errorMessage.postValue(null)
    }

}
