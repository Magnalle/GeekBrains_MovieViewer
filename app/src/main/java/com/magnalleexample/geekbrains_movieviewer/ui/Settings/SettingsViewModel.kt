package com.magnalleexample.geekbrains_movieviewer.ui.Settings

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Language
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import com.magnalleexample.geekbrains_movieviewer.ui.home.ERROR_LOADING_FROM_REPOSITORY
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel : ViewModel() {
    var repo: Repo? = null
    val languagesList : MutableLiveData<List<Language>> = MutableLiveData()
    private var _errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    val errorMessage
        get() = _errorMessage
    private val exceptionHandler = CoroutineExceptionHandler { _, error ->
        error.message?.let {
            Log.d("Repo", "$ERROR_LOADING_FROM_REPOSITORY$it")
            _errorMessage.postValue("$ERROR_LOADING_FROM_REPOSITORY$it")
        }
    }
    fun loadData(){
        viewModelScope.launch(exceptionHandler) {
            _loadData()
        }
    }
    private suspend fun _loadData() = withContext(Dispatchers.IO) {
        languagesList.postValue(repo?.getLanguagesList()?: listOf())
    }

    fun disableError() {
        errorMessage.postValue(null)
    }

}