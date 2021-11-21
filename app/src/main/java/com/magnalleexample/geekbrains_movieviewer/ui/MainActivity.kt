package com.magnalleexample.geekbrains_movieviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.magnalleexample.geekbrains_movieviewer.R
import com.magnalleexample.geekbrains_movieviewer.databinding.MainActivityBinding
import com.magnalleexample.geekbrains_movieviewer.ui.home.HomeFragment
import android.net.ConnectivityManager

import android.content.IntentFilter
import com.magnalleexample.geekbrains_movieviewer.MyReceiver


class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    val myReceiver: MyReceiver = MyReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_top
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(myReceiver, filter)
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver)
    }
}