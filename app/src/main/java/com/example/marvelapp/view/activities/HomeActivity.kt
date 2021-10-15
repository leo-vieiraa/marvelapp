package com.example.marvelapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelapp.R
import com.example.marvelapp.view.fragments.HomeListingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_home_listing, HomeListingFragment.newInstance())
            .commitNow()

    }
}