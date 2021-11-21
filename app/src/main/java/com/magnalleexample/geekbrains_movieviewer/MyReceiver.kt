package com.magnalleexample.geekbrains_movieviewer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Context.CONNECTIVITY_SERVICE

import androidx.core.content.ContextCompat.getSystemService

import android.net.ConnectivityManager
import android.widget.Toast
import androidx.core.content.ContextCompat


class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val noConnectivity =
            intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
        if(noConnectivity)
            Toast.makeText(context,"No connection", Toast.LENGTH_LONG).show()
    }
}