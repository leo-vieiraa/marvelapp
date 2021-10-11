package com.example.marvelapp.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.adapter.SearchAdapter
import com.example.marvelapp.adapter.SuperHeroHomeAdapter
import com.example.marvelapp.databinding.FragmentHomeVerticalListingBinding
import com.example.marvelapp.model.SuperHero
import com.example.marvelapp.viewmodel.HomeVerticalListing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeVerticalListing : Fragment(R.layout.fragment_home_vertical_listing) {

    companion object {
        fun newInstance() = HomeVerticalListing()
    }

    private lateinit var verticalListing: HomeVerticalListing
    private lateinit var binding: FragmentHomeVerticalListingBinding

//   ADAPTERS
    var clearList = false
    private val searchAdapter = SearchAdapter() {
        clearList = true
        if (it.isEmpty()) {
            verticalListing.searchFor(null)
        } else {
            verticalListing.searchFor(it)
        }
    }
    private val superHeroHomeAdapter = SuperHeroHomeAdapter ()

//   OBSERVERS
    private val observerSuperHeroList = Observer<List<SuperHero>> {
        superHeroHomeAdapter.submitList(it)
    }
    private val observerPages = Observer<Int> {
        verticalListing.fetchSuperHeroes(page = it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeVerticalListingBinding.bind(view)
        verticalListing = ViewModelProvider(this).get(HomeVerticalListing::class.java)
        verticalListing.superHeroList.observe(viewLifecycleOwner, observerSuperHeroList)
        verticalListing.page.observe(viewLifecycleOwner, observerPages)

        setupRecyclerView()
    }

    fun setupRecyclerView() = with(binding.rvHomeListingHeroList) {

        adapter = ConcatAdapter(searchAdapter, superHeroHomeAdapter)
        layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == 0) 2 else 1
                }
            }
        }
        verticalListing.nextPage()

    }

}