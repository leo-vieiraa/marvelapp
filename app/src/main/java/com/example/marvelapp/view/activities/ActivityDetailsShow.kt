package com.example.marvelapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.marvelapp.databinding.ActivityDetailsShowBinding
import com.example.marvelapp.model.SuperHero
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityDetailsShow : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupDetails()
    }

    private fun setupToolbar() {
        binding.ivDetailsShowArrowBack.setOnClickListener {
            finish()
        }
    }

    private fun setupDetails() {

        val superHero = intent.extras?.getSerializable("hero") as SuperHero

        binding.cvDetailsShow
            .setImage(baseContext, "${superHero.thumbnail.path}.${superHero.thumbnail.extension}")

        binding.cvDetailsShow.setData(superHero.name, superHero.description)

    }

}