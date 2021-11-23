package com.magnalleexample.geekbrains_movieviewer

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.io.OutputStreamWriter

class LogService : Service() {

    val logBinder = LogBinder()

    override fun onBind(intent: Intent): IBinder {
        logBinder.onBind(this)
        return logBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        logBinder.onUnBind()
        return super.onUnbind(intent)
    }

    class LogBinder: Binder(){
        private var file : OutputStreamWriter? = null
        fun onBind(context : Context){
            context.openFileInput("log.txt").bufferedReader()
            file = OutputStreamWriter(context.openFileOutput("log.txt", Context.MODE_APPEND))
        }
        fun onUnBind(){
            file?.close()
            file = null
        }
        fun log(str: String){
            file?.write("$str\n")
        }
    }
}