package com.magnalleexample.geekbrains_movieviewer

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.magnalleexample.geekbrains_movieviewer.data.WorkingRepo
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo

class App : Application() {
    val repository : Repo by lazy { WorkingRepo() }
    var logBinder : LogService.LogBinder? = null
    val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            logBinder = binder as LogService.LogBinder
        }
        override fun onServiceDisconnected(name: ComponentName?) {
            logBinder = null
        }
    }
}

val Context.app
    get() = applicationContext as App

fun Context.log(str: String){
    app.logBinder?.log(str)
}