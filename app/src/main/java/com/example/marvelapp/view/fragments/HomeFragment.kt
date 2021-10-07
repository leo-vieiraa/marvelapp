package com.example.marvelapp.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.adapter.SuperHeroHomeAdapter
import com.example.marvelapp.databinding.HomeFragmentBinding
import com.example.marvelapp.model.SuperHero
import com.example.marvelapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding
    private val superHeroHomeAdapter = SuperHeroHomeAdapter ()

    private val observerSuperHeroList = Observer<List<SuperHero>> {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.superHeroList.observe(viewLifecycleOwner, observerSuperHeroList)

        setupRecyclerView()
        viewModel.fetchSuperHeroes()

    }

    fun setupRecyclerView() = with(binding.superheroHorizontalRecyclerView) {

        adapter = superHeroHomeAdapter
        layoutManager = GridLayoutManager(requireContext(), 2)

    }

}