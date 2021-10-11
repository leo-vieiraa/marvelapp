package com.example.marvelapp.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelapp.R
import com.example.marvelapp.viewmodel.ViewModelDetailsShow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetailsShow : Fragment(R.layout.fragment_details_show) {

    companion object {
        fun newInstance() = FragmentDetailsShow()
    }

    private lateinit var viewModel: ViewModelDetailsShow

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModelDetailsShow::class.java)

        val a = arguments?.getSerializable("hero")

    }

}