package com.example.marvelapp.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import okhttp3.internal.wait

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class ActivitySplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_app)

        CoroutineScope(Dispatchers.Main).launch {
            showSplash()
            callHomeActivity()
        }

    }

    suspend fun showSplash(): Boolean {
        delay(100)
        return true
    }

    fun callHomeActivity() {
        val intent = Intent(this, ActivityHome::class.java)
        startActivity(intent)
    }

}