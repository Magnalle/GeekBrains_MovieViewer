package com.magnalleexample.geekbrains_movieviewer.ui.movie

import androidx.lifecycle.ViewModel
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo

class MovieViewModel : ViewModel() {
    lateinit var repo : Repo
}