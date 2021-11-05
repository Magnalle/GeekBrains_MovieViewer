package com.magnalleexample.geekbrains_movieviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.magnalleexample.geekbrains_movieviewer.ui.main.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HomeFragment())
                    .commitNow()
        }
    }
}