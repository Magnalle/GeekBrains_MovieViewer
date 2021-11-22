package com.magnalleexample.geekbrains_movieviewer

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
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

fun Context.bindLog(){
    val serviceIntent = Intent(this, LogService::class.java)
    bindService(serviceIntent, this.app.connection, AppCompatActivity.BIND_AUTO_CREATE)
}

fun Context.unBindLog(){
    unbindService(app.connection)
}