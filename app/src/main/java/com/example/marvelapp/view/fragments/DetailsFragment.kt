package com.example.marvelapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentDetailsBinding
import com.example.marvelapp.model.SuperHero

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        val superHero = arguments?.getSerializable("superHero") as SuperHero

        binding.cvDetailsShow
            .setImage(requireContext(), "${superHero.thumbnail.path}.${superHero.thumbnail.extension}")
        binding.cvDetailsShow.setData(superHero.name, superHero.description)
    }
}