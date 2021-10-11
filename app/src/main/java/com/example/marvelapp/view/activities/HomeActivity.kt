package com.example.marvelapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelapp.R
import com.example.marvelapp.view.fragments.HomeVerticalListing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeVerticalListing.newInstance())
            .commitNow()

    }
}