package com.skyapp.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.skyapp.newsapp.data.datastore.UserPreferences
import com.skyapp.newsapp.data.datastore.UserPrefsManager
import com.skyapp.newsapp.ui.navigation.NewsApp
import com.skyapp.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate: ")
        enableEdgeToEdge()
        setContent {
            NewsAppTheme(
            ) {
                NewsApp()
            }
        }

    }

    override fun onStart() { //app is partially visible to the user and not interactive
        super.onStart()
        Log.d("MainActivity", "onStart: ")
    }

    override fun onResume() { // app is fully visible to user and interactive
        super.onResume()
        Log.d("MainActivity", "onResume: ")
    }
    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause: ")
    }
    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy: ")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart: ")
    }
}



