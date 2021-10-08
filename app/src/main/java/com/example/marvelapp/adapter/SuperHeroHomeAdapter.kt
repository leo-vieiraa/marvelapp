package com.example.marvelapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentHomeVerticalListingItemBinding
import com.example.marvelapp.model.SuperHero

class SuperHeroHomeAdapter : ListAdapter<SuperHero, SuperHeroHomeViewHolder>(DiffUtilItemCallback()) {

    private val listOfSuperHeroes = mutableListOf<SuperHero>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroHomeViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_vertical_listing_item, parent, false).apply {
            return SuperHeroHomeViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: SuperHeroHomeViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it)
        }
    }

}

class SuperHeroHomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = FragmentHomeVerticalListingItemBinding.bind(view)

    fun bind(superHero: SuperHero) {

        Glide.with(itemView)
            .load("${superHero.thumbnail.path}.${superHero.thumbnail.extension}")
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.ivHomeListingHeroPhoto)

        binding.tvHomeListingHeroName.text = superHero.name

    }

}