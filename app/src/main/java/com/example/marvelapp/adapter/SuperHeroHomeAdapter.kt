package com.example.marvelapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentHomeListingVerticalItemBinding
import com.example.marvelapp.model.SuperHero

class SuperHeroHomeAdapter(
    private val listingType: ListingType,
    private val onclick: (SuperHero) -> Unit
    ) : ListAdapter<SuperHero, SuperHeroHomeViewHolder>(DiffUtilItemCallback()) {

    private val listOfSuperHeroes = mutableListOf<SuperHero>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroHomeViewHolder {

        LayoutInflater.from(parent.context).inflate(
             R.layout.fragment_home_listing_vertical_item, parent, false).apply {

            return SuperHeroHomeViewHolder(this)

        }
    }

    override fun onBindViewHolder(holder: SuperHeroHomeViewHolder, position: Int) {
        getItem(position).let { superhero ->
            holder.bind(superhero)
            holder.itemView.setOnClickListener { onclick(superhero) }
        }
    }

    fun update(newList: List<SuperHero>, clear: Boolean = false) {
        if (clear) {
            listOfSuperHeroes.clear()
            listOfSuperHeroes.addAll(newList)
            submitList(listOfSuperHeroes.toMutableList())
        }
        listOfSuperHeroes.addAll(newList)
        submitList(listOfSuperHeroes.toMutableList())
    }

}

class SuperHeroHomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = FragmentHomeListingVerticalItemBinding.bind(view)

    fun bind(superHero: SuperHero) {

        binding.cvHomeHeroCard.setImage(itemView, "${superHero.thumbnail.path}.${superHero.thumbnail.extension}")
        binding.cvHomeHeroCard.setName(superHero.name)

    }

}