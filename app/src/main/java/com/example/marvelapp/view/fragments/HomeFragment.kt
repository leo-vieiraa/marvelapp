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
import com.example.marvelapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home_vertical_listing) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeVerticalListingBinding

//   ADAPTERS
    var clearList = false
    private val searchAdapter = SearchAdapter() {
        clearList = true
        if (it.isEmpty()) {
            viewModel.searchFor(null)
        } else {
            viewModel.searchFor(it)
        }
    }
    private val superHeroHomeAdapter = SuperHeroHomeAdapter ()

//   OBSERVERS
    private val observerSuperHeroList = Observer<List<SuperHero>> {
        superHeroHomeAdapter.submitList(it)
    }
    private val observerPages = Observer<Int> {
        viewModel.fetchSuperHeroes(page = it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeVerticalListingBinding.bind(view)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.superHeroList.observe(viewLifecycleOwner, observerSuperHeroList)
        viewModel.page.observe(viewLifecycleOwner, observerPages)

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
        viewModel.nextPage()

    }

}