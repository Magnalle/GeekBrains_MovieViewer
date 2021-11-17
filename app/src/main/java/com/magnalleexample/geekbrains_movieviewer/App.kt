package com.magnalleexample.geekbrains_movieviewer

import android.app.Application
import android.content.Context
import com.magnalleexample.geekbrains_movieviewer.data.WorkingRepo
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo

class App : Application() {
    val repository : Repo by lazy { WorkingRepo() }
}

val Context.app
    get() = applicationContext as App