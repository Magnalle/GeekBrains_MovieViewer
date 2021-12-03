package com.magnalleexample.geekbrains_movieviewer.ui.movie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import com.magnalleexample.geekbrains_movieviewer.ui.home.ERROR_LOADING_FROM_REPOSITORY
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel : ViewModel() {
    lateinit var repo : Repo
    lateinit var movieData : MovieData

    private val exceptionHandler = CoroutineExceptionHandler { _, error ->
        error.message?.let {
            Log.d("Repo", "$ERROR_LOADING_FROM_REPOSITORY$it")
            _errorMessage.postValue("$ERROR_LOADING_FROM_REPOSITORY$it")
        }
    }
    private var _errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    val errorMessage
        get() = _errorMessage

    suspend fun _synchMovieData() = withContext(Dispatchers.IO){
        repo.synchMovieData(movieData)
    }

    fun synchMovieData(){
        viewModelScope.launch(exceptionHandler) {
            _synchMovieData()
        }
    }

    fun setMovieInWatchList(value : Boolean) {
        viewModelScope.launch(exceptionHandler) {
            _setMovieInWatchList(value)
        }
    }

    fun setMovieInFavorites(value : Boolean) {
        viewModelScope.launch(exceptionHandler) {
            _setMovieInFavorites(value)
        }
    }

    suspend fun _setMovieInWatchList(value : Boolean) = withContext(Dispatchers.IO){
        repo.setMovieInWatchList(movieData, value)
    }

    suspend fun _setMovieInFavorites(value : Boolean) = withContext(Dispatchers.IO){
        repo.setMovieInFavorites(movieData, value)
    }
}