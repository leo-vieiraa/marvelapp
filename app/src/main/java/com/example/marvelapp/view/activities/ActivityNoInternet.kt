package com.example.marvelapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelapp.R
import com.example.marvelapp.utils.checkForInternet

class ActivityNoInternet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)



    }
}