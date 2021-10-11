package com.example.marvelapp.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.adapter.ListingType
import com.example.marvelapp.adapter.SearchAdapter
import com.example.marvelapp.adapter.SuperHeroHomeAdapter
import com.example.marvelapp.databinding.FragmentHomeVerticalListingBinding
import com.example.marvelapp.model.SuperHero
import com.example.marvelapp.viewmodel.ViewModelHomeListing
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import com.example.marvelapp.view.activities.ActivityDetailsShow


@AndroidEntryPoint
class HomeVerticalListingFragment : Fragment(R.layout.fragment_home_vertical_listing) {

    companion object {
        fun newInstance() = HomeVerticalListingFragment()
    }

    private lateinit var listingViewModelHomeListing: ViewModelHomeListing
    private lateinit var binding: FragmentHomeVerticalListingBinding

//   ADAPTERS
    private var clearList = false
    private val searchAdapter = SearchAdapter() {
        clearList = true
        if (it.isEmpty()) {
            listingViewModelHomeListing.searchFor(null)
        } else {
            listingViewModelHomeListing.searchFor(it)
        }
    }
    private val superHeroHomeAdapter = SuperHeroHomeAdapter (ListingType.VERTICAL) {

        val i = Intent(requireActivity(), ActivityDetailsShow::class.java)
        i.putExtra("hero", it)
        startActivity(i)

    }

//   OBSERVERS
    private val observerSuperHeroList = Observer<List<SuperHero>> {
        superHeroHomeAdapter.submitList(it)
    }
    private val observerPages = Observer<Int> {
        listingViewModelHomeListing.fetchSuperHeroes(page = it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeVerticalListingBinding.bind(view)
        listingViewModelHomeListing = ViewModelProvider(this).get(ViewModelHomeListing::class.java)
        listingViewModelHomeListing.superHeroList.observe(viewLifecycleOwner, observerSuperHeroList)
        listingViewModelHomeListing.page.observe(viewLifecycleOwner, observerPages)

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
        listingViewModelHomeListing.nextPage()

    }

}