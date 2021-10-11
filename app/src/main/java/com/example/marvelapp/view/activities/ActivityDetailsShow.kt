package com.example.marvelapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelapp.R
import com.example.marvelapp.view.fragments.FragmentDetailsShow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityDetailsShow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_show)

        val extras = intent.extras
        if (extras != null) {

            val bundle = Bundle()
            bundle.putSerializable("hero", extras.getSerializable("hero"))
            val fragment = FragmentDetailsShow()
            fragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_details_show_container, fragment)
                .commitNow()

        }

    }
}