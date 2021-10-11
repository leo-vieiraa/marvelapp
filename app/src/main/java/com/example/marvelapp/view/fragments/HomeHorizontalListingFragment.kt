package com.example.marvelapp.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.adapter.ListingType
import com.example.marvelapp.adapter.SuperHeroHomeAdapter
import com.example.marvelapp.databinding.FragmentHomeHorizontalListingBinding
import com.example.marvelapp.model.SuperHero
import com.example.marvelapp.viewmodel.ViewModelHomeListing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeHorizontalListingFragment : Fragment(R.layout.fragment_home_horizontal_listing) {

    companion object {
        fun newInstance() = HomeHorizontalListingFragment()
    }

    private lateinit var viewModelHomeListing: ViewModelHomeListing
    private lateinit var binding: FragmentHomeHorizontalListingBinding
    private val superHeroHomeAdapter = SuperHeroHomeAdapter(ListingType.HORIZONTAL) {

    }

    private val observerSuperHeroList = Observer<List<SuperHero>> {
        superHeroHomeAdapter.submitList(it)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeHorizontalListingBinding.bind(view)

        viewModelHomeListing = ViewModelProvider(this).get(ViewModelHomeListing::class.java)
        viewModelHomeListing.superHeroList.observe(viewLifecycleOwner, observerSuperHeroList)

        setupRecyclerView()

        viewModelHomeListing.fetchSuperHeroes(1, 5)

    }

    fun setupRecyclerView() = with(binding.rvHomeListingHeroList){

        adapter = superHeroHomeAdapter
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

    }

}