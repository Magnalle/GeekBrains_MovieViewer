package com.magnalleexample.geekbrains_movieviewer.ui.top

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import com.magnalleexample.geekbrains_movieviewer.ui.home.ERROR_LOADING_FROM_REPOSITORY
import com.magnalleexample.geekbrains_movieviewer.ui.home.HomeFragmentDirections
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopViewModel : ViewModel() {
    lateinit var repo : Repo
    lateinit var sharedPreferences: SharedPreferences
    val genresList : MutableLiveData<List<Genre>> = MutableLiveData()
    val topList : MutableLiveData<List<MovieData>> = MutableLiveData()
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
        topList.postValue(repo.getTopList(repo, sharedPreferences.getBoolean("enable_Adult_switch", false), ))
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

    fun disableError() {
        errorMessage.postValue(null)
    }
}