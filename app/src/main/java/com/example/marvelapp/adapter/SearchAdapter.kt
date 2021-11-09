package com.example.marvelapp.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentHomeListingSearchBinding

class SearchAdapter(private val ontype: (String) -> Unit) : RecyclerView.Adapter<SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_listing_search, parent, false).apply {
            return SearchViewHolder(this, ontype)
        }
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 1

}

class SearchViewHolder(view: View, val ontype: (String) -> Unit) : RecyclerView.ViewHolder(view) {

    private val binding = FragmentHomeListingSearchBinding.bind(view)

    fun bind() {

        binding.etHomeListingHeroSearch.setSearchListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let {
                    if (it.length > 2) {
                        ontype(it.toString())
                    }
                    if (it.isEmpty()) {
                        ontype(it.toString())
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

    }

}