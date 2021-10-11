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

class SuperHeroHomeAdapter(
    private val listingType: ListingType,
    private val onclick: (SuperHero) -> Unit
    ) : ListAdapter<SuperHero, SuperHeroHomeViewHolder>(DiffUtilItemCallback()) {

    private val listOfSuperHeroes = mutableListOf<SuperHero>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroHomeViewHolder {

        LayoutInflater.from(parent.context).inflate(
            if (listingType == ListingType.VERTICAL) R.layout.fragment_home_vertical_listing_item
                else R.layout.fragment_home_horizontal_listing_item
            , parent, false).apply {

            return SuperHeroHomeViewHolder(this)

        }
    }

    override fun onBindViewHolder(holder: SuperHeroHomeViewHolder, position: Int) {
        getItem(position).let { superhero ->
            holder.bind(superhero)
            holder.itemView.setOnClickListener { onclick(superhero) }
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