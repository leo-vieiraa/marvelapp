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
import com.example.marvelapp.model.SuperHero
import com.example.marvelapp.viewmodel.ViewModelHomeListing
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.marvelapp.databinding.FragmentHomeListingBinding
import com.example.marvelapp.view.activities.ActivityDetailsShow


@AndroidEntryPoint
class HomeListingFragment : Fragment(R.layout.fragment_home_listing) {

    companion object {
        fun newInstance() = HomeListingFragment()
    }

    private lateinit var listingViewModelHomeListing: ViewModelHomeListing
    private lateinit var binding: FragmentHomeListingBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

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
    private val superHeroHomeAdapterHorizontal = SuperHeroHomeAdapter (ListingType.HORIZONTAL) {

        val i = Intent(requireActivity(), ActivityDetailsShow::class.java)
        i.putExtra("hero", it)
        startActivity(i)

    }
    private val superHeroHomeAdapterVertical = SuperHeroHomeAdapter (ListingType.VERTICAL) {

        val i = Intent(requireActivity(), ActivityDetailsShow::class.java)
        i.putExtra("hero", it)
        startActivity(i)

    }

//   OBSERVERS
    private val observerQuery = Observer<String?> {}
    private val observerSuperHeroList = Observer<List<SuperHero>> {

        superHeroHomeAdapterHorizontal.update(it)

        /**
         * When the user search for something, the main list is cleared and the
         * search results are loaded
         */
        if (listingViewModelHomeListing.query.value.isNullOrEmpty()) {
            superHeroHomeAdapterVertical.update(it)
        } else {
            superHeroHomeAdapterVertical.update(it, true)
        }

        swipeRefreshLayout.isRefreshing = false
    }
    private val observerPages = Observer<Int> {

        /**
         * When the user search for something, the main list is cleared and the
         * search results are loaded
         */
        if (listingViewModelHomeListing.query.value.isNullOrEmpty()){
            listingViewModelHomeListing.fetchSuperHeroes(page = it)
        } else {
            listingViewModelHomeListing.fetchSuperHeroes(0)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeListingBinding.bind(view)
        listingViewModelHomeListing = ViewModelProvider(this).get(ViewModelHomeListing::class.java)
        listingViewModelHomeListing.superHeroList.observe(viewLifecycleOwner, observerSuperHeroList)
        listingViewModelHomeListing.page.observe(viewLifecycleOwner, observerPages)
        listingViewModelHomeListing.query.observe(viewLifecycleOwner, observerQuery)

        swipeRefreshLayout = binding.srlHomeListingHeroList
        setupHorizontalRecyclerView()
        setupVerticalRecyclerView()
    }

    fun setupHorizontalRecyclerView() = with(binding.rvHomeListingHeroHorizontalList){

        adapter = superHeroHomeAdapterHorizontal
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        listingViewModelHomeListing.fetchSuperHeroes(0, 5)

    }


    fun setupVerticalRecyclerView() = with(binding.rvHomeListingHeroVerticalList) {

        adapter = ConcatAdapter(searchAdapter, superHeroHomeAdapterVertical)
        layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == 0) 2 else 1
                }
            }
        }
        listingViewModelHomeListing.nextPage()

        binding.rvHomeListingHeroVerticalList.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.srlHomeListingHeroList.isRefreshing = true
                    listingViewModelHomeListing.nextPage()
                }
            }
        })

        binding.srlHomeListingHeroList.setOnRefreshListener {
            binding.srlHomeListingHeroList.isRefreshing = false
        }

    }

}