package com.magnalleexample.geekbrains_movieviewer.ui.home

import android.R
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.magnalleexample.geekbrains_movieviewer.data.net.MoviesApiService
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo
import com.magnalleexample.geekbrains_movieviewer.ui.movieList.MovieDataListener
import com.magnalleexample.geekbrains_movieviewer.ui.movieList.MovieListAdapter

class HomePresenter : HomeInterface.Presenter{

    override var view: HomeInterface.View? = null
    override val genresList: List<Genre> = MoviesApiService.getGenres()
    override val watchList: List<MovieData> = Repo.watchList
    override val favorites: List<MovieData> = Repo.favorites


    override fun attach(view: HomeInterface.View) {
        this.view = view
        view.updateFavoritesList()
        view.updateWatchList()
    }


    override fun detach() {
        this.view = null
    }

    override fun getGenresListFormatted(): List<String> {
        return listOf("All genres").plus(genresList.map { it.name })
    }


}