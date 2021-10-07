package com.example.marvelapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.marvelapp.model.SuperHero

class DiffUtilItemCallback : DiffUtil.ItemCallback<SuperHero>() {
    override fun areItemsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean {
        return oldItem == newItem
    }
}