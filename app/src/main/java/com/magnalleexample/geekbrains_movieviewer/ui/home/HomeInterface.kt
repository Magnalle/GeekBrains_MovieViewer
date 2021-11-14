package com.magnalleexample.geekbrains_movieviewer.ui.home

import androidx.lifecycle.MutableLiveData
import com.magnalleexample.geekbrains_movieviewer.data.net.MoviesApiService
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo

class HomeInterface {
    interface ViewModel{
        val genresList : List<Genre>
        val watchList : MutableLiveData<List<MovieData>>
        val favorites : MutableLiveData<List<MovieData>>
    }
}